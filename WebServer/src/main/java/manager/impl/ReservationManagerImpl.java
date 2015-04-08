package manager.impl;

import manager.ReservationManager;
import mybatis.dao.ReservationMapper;
import mybatis.handler.TwoLongParameters;
import mybatis.model.basic.Reservation;
import mybatis.model.complex.ReservationWithUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Irrielde on 4.1.2015.
 */

@Service
public class ReservationManagerImpl implements ReservationManager {

    /*
    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private EventManager eventManager;
    */

    @Autowired
    private ReservationMapper reservationMapper;


    @Override
    public Long createReservation(Reservation reservation) {

        try {
            int returned = reservationMapper.insert(reservation);

            if (returned > 0) {
                return reservation.getId();
            } else {
                return -1L;
            }

        }catch (DuplicateKeyException duplicateKeyException){
            System.err.println("Duplicate reservation insert attempted, user:" +
                    reservation.getIdUser() + " event:" + reservation.getIdEvent());
            return -2L;
        }


    }

    @Override
    public List<Reservation> getMyPendingInvitations(Long id) {
        return reservationMapper.selectPendingInvitationsByUserID(id);
    }

    @Override
    public List<Reservation> getReservationsUserConfirmed(Long id) {
        return reservationMapper.selectReservationsUserConfirmedByUserID(id);
    }

    @Override
    public Boolean confirmUsersReservation(Long reservationId, Long idUser) {

        Reservation reservation = reservationMapper.selectByPrimaryKey(reservationId);

        System.out.println("porovnavam " + reservation.getIdUser() + " " + idUser);

        if (reservation.getIdUser() == idUser) {

            reservation.setConfirmed(true);
            reservation.setConfirmPending(false);
            reservation.setInvitationPending(false);

            if (reservationMapper.updateByPrimaryKey(reservation) > 0) {
                return true;
            } else {
                System.out.println("error pri updatu");
                return false;
            }


        }

        System.out.println("nerovno");

        return false;
    }

    @Override
    public Boolean declineUsersReservation(Long reservationId, Long idUser) {

        Reservation reservation = reservationMapper.selectByPrimaryKey(reservationId);

        if (reservation.getIdUser() == idUser) {

            if (reservationMapper.deleteByPrimaryKey(reservationId) > 0) {
                return true;
            } else {
                return false;
            }

        }


        return false;
    }

    @Override
    public Boolean confirmReservation(Long reservationId) {

        Reservation reservation = reservationMapper.selectByPrimaryKey(reservationId);


        reservation.setConfirmed(true);
        reservation.setConfirmPending(false);
        reservation.setInvitationPending(false);

        if (reservationMapper.updateByPrimaryKey(reservation) > 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Boolean declineReservation(Long reservationId) {


        if (reservationMapper.deleteByPrimaryKey(reservationId) > 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<Reservation> getReservationsForEvent(Long eventId) {

        List<Reservation> reservations = reservationMapper.selectReservationsForEventByEventId(eventId);



        return reservations;
    }

    @Override
    public List<ReservationWithUser> getReservationsWithUsersForEvent(Long eventId) {

        List<ReservationWithUser> reservations = reservationMapper.selectReservationsWithUserForEventByEventId(eventId);


        return reservations;
    }

    @Override
    public boolean cancelReservationByEventUserIDs(Long userID, Long eventID) {

        TwoLongParameters twoLongParameters = new TwoLongParameters(userID,eventID);

        if (reservationMapper.deleteByUserAndEventIDs(twoLongParameters) > 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int setAttendedForReservation(Long userId, Long eventId, boolean attended) {

        Reservation reservation = new Reservation();
        reservation.setAttended(attended);
        reservation.setIdEvent(eventId);
        reservation.setIdUser(userId);

        try{

            int rowsAffected = reservationMapper.updateByForeignKeysSelective(reservation);

            if(rowsAffected>0){
                return 0;
            }else{
                return 1;
            }

        }catch (Exception ex){
            System.err.println("Updating for attended:");
            System.err.println(ex.toString());
            return 2;
        }


    }
}