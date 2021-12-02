package com.tracom.events.scheduler.Venues;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BoardroomService {
    @Autowired
    private BoardroomRepository bRepo;

    /** LIST or READ **/

    public List<Boardroom>listBoardrooms(){
        return  bRepo.findAll();
    }

    public int noOfRooms(){
        return bRepo.numberOfRooms();
    }

    public List<Boardroom> ShowRoomsByOrganization(int organization_id){
        return bRepo.findAllVenuesByOrganization(organization_id);
    }

    /** CREATE **/
    public  void saveBoardroom(Boardroom boardroom){
       Optional<Boardroom> findByBoardroomName = Optional.ofNullable(bRepo.findByBoardroomName(boardroom.getBoardroom_name()));
        if (findByBoardroomName.isPresent()) {

            throw new IllegalStateException("Boardroom with the same name Already Exists!");
        }

            bRepo.save(boardroom);
    }

    /** UPDATE **/
    public Boardroom updateBoardroom(Integer boardroom_id){
       return bRepo.findById(boardroom_id).get();
    }
    /** 2ND UPDATE method with void return type **/
    public void updateBoardroom1(Boardroom boardroom){
        bRepo.findByBoardroom_id(boardroom.getBoardroom_id());
    }



    /** DELETE **/

    public void deleteBoardroom(Integer boardroom_id){
        bRepo.findById(boardroom_id);

    }
    /**  2ND DELETE **/
    public void deleteBoardroom1(Boardroom boardroom){
        bRepo.findByBoardroom_id(boardroom.getBoardroom_id());
    }


}
