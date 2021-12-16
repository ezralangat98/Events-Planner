package net.office_planner.Notifications;


import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import net.office_planner.Meetings.MeetingRepository;
import net.office_planner.Meetings.Meetings;
import net.office_planner.User.User;
import net.office_planner.User.UserRepository;
import net.office_planner.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.management.Notification;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.*;

@EnableScheduling
@Component
@NoArgsConstructor
@Transactional
@Service
public class NotificationService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private JavaMailSender mailSender;

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
    private List<ScheduledFuture<?>> scheduledFutures = new ArrayList<>();


    /** Sending email notification **/
    private void sendMail1(User user, Meetings meeting) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        /** Getting Full name(s) and email(s) of the Owner(s)  **/
        String email = user.getEmail();
        String fName = user.getFirstName();
        String lName = user.getLastName();


        helper.setFrom("bianalyst77@gmail.com", "Office Meeting Planner Support");
        helper.setTo(email);

        String subject = "Meeting Notification";

        String content = "<p>Hello " + fName + " " + lName + ",</p>"
                + "<p>The " + meeting.getMeeting_name() + " for  " + meeting.getOrganization().getOrganization_name() +" is scheduled to commence today at " + meeting.getStartTime() + " .</p>"
                + "<p>The venue of the meeting will be at " + meeting.getBoardroom().getBoardroom_name() + " .</p>"
                + "<p>Kindly avail yourself in time.</p>"
                + "<p><br></p>"
                + "<p>Regards,</p>"
                + "<p>Office Meeting Planner Support.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }

    /** End of Sending email notifications **/




    public void notificationTimer(Meetings meeting, User user) throws ExecutionException, InterruptedException {
        LocalDateTime time = LocalDateTime.of(meeting.getMeetingDate(),
                meeting.getStartTime()).minusMinutes(15);

        long sendTime = time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println("Scheduling....");
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.
                schedule(new Notification(user, meeting),sendTime, TimeUnit.MILLISECONDS);
        scheduledFutures.add(scheduledFuture);
    }

    @Scheduled(fixedDelay = 17 * 60 * 1000)
    public void sendNotifications(){
        try {
            scheduledFutures.forEach(f -> f.cancel(true));
        }catch (Exception e){
            e.getMessage();
        }
        List<Meetings> meetings = meetingRepository.findAll();
        // TODO: 12/1/2021 Filter to load current Dates
        meetings.forEach(r ->{
            if (r.getStartTime().isAfter(LocalTime.now().plusMinutes(16))) {
                r.getUsers().forEach(u -> {
                    try {
                        notificationTimer(r,u);
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    public class Notification extends TimerTask {
        
        User user;
        Meetings meet;

        public Notification(User user, Meetings meet) {
            this.user = user;
            this.meet = meet;
        }


        @SneakyThrows
        @Override
        public void run() {
            System.out.println("Scheduling the meeting");
            try {
                sendMail1(user,meet);
                System.out.println("Sent the mail");
            }finally
            {
                System.out.println("Failed to send mail  ");
                try {
                    sendMail1(user,meet);
                    System.out.println("Sent the sms");
                }finally {
                    System.out.println("Failed to send the SMS");
                }
            }
        }
    }
}

