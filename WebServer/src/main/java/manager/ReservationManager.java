package manager;

import mybatis.model.basic.Reservation;
import mybatis.model.complex.ReservationWithUser;

import java.util.List;

/**
 * Created by Irrielde on 4.1.2015.
 */
public interface ReservationManager {

    Long createReservation(Reservation reservation);

    List<Reservation> getMyPendingInvitations(Long id);

    List<Reservation> getReservationsUserConfirmed(Long id);

    Boolean confirmUsersReservation(Long reservationId, Long idUser);

    Boolean declineUsersReservation(Long reservation_id, Long id);

    Boolean confirmReservation(Long reservationId);

    Boolean declineReservation(Long reservationId);

    List<Reservation> getReservationsForEvent(Long eventId);

    List<ReservationWithUser> getReservationsWithUsersForEvent(Long event_id);

    boolean cancelReservationByEventUserIDs(Long userID, Long eventID);

    int setAttendedForReservation(Long userId, Long eventId, boolean attended);

}
