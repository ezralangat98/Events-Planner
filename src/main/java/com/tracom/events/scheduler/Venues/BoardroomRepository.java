package com.tracom.events.scheduler.Venues;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardroomRepository extends JpaRepository<Boardroom, Integer> {
    @Query("SELECT b FROM Boardroom b WHERE b.boardroom_id= ?1")
    Boardroom findByBoardroom_id(Integer boardroom_id);

    @Query("SELECT b FROM Boardroom b WHERE b.boardroom_name= ?1")
    Boardroom findByBoardroomName(String boardroom_name);

    @Query("SELECT b FROM Boardroom b WHERE b.organization.organization_id = ?1")
    List<Boardroom> findAllVenuesByOrganization(int organization);


    @Query("SELECT COUNT(b.boardroom_id) FROM Boardroom b")
    int numberOfRooms();




    //Organization getById(Integer organization_id);

}

