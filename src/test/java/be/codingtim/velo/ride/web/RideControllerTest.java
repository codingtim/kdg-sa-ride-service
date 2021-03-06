package be.codingtim.velo.ride.web;

import be.codingtim.velo.ride.domain.ride.RideId;
import be.codingtim.velo.ride.domain.ride.StationRideStarted;
import be.codingtim.velo.ride.domain.ride.exception.NoActiveRideForUser;
import be.codingtim.velo.ride.domain.ride.exception.OnlyFreeRideVehicleCanBeLockedAnywhere;
import be.codingtim.velo.ride.domain.ride.exception.OnlyOneActiveRideAllowed;
import be.codingtim.velo.ride.domain.ride.exception.OnlyStationVehicleCanBeLockedAtStation;
import be.codingtim.velo.ride.domain.station.LockId;
import be.codingtim.velo.ride.domain.station.StationId;
import be.codingtim.velo.ride.domain.station.exception.StationHasNoAvailableVehicle;
import be.codingtim.velo.ride.domain.station.exception.StationNotFound;
import be.codingtim.velo.ride.domain.user.UserId;
import be.codingtim.velo.ride.domain.user.exception.UserHasNoActiveSubscription;
import be.codingtim.velo.ride.domain.vehicle.VehicleId;
import be.codingtim.velo.ride.domain.vehicle.exception.VehicleNotFound;
import be.codingtim.velo.ride.facade.RideFacade;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RideControllerTest {

    private final RideFacade rideFacade = Mockito.mock(RideFacade.class);
    private final MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(new RideController(rideFacade))
            .setControllerAdvice(new ControllerExceptionAdvice())
            .build();


    @Nested
    class StartStationRide {

        private final UserId userId = new UserId(123);
        private final StationId stationId = new StationId(125);
        private final LockId lockId = new LockId(214);
        private final RideId rideId = new RideId(12345);
        private final StationRideStarted stationRideStarted = new StationRideStarted() {
            @Override
            public RideId getRideId() {
                return rideId;
            }

            @Override
            public LockId getStartLockId() {
                return lockId;
            }
        };

        @Test
        void ok() throws Exception {
            whenStartStationRide().thenReturn(stationRideStarted);
            callStartStationRide()
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.lockId").value(lockId.getValue()))
                    .andExpect(header().string(LOCATION, "/api/rides/12345"))
            ;
        }

        @Test
        void noSubscription() throws Exception {
            whenStartStationRide().thenThrow(new UserHasNoActiveSubscription());
            callStartStationRide()
                    .andExpect(status().isForbidden())
            ;
        }

        @Test
        void hasAnotherActiveRide() throws Exception {
            whenStartStationRide().thenThrow(new OnlyOneActiveRideAllowed());
            callStartStationRide()
                    .andExpect(status().isForbidden())
            ;
        }

        @Test
        void stationHasNoVehicle() throws Exception {
            whenStartStationRide().thenThrow(new StationHasNoAvailableVehicle(stationId));
            callStartStationRide()
                    .andExpect(status().isPreconditionFailed())
            ;
        }

        @Test
        void stationNotFound() throws Exception {
            whenStartStationRide().thenThrow(new StationNotFound(stationId));
            callStartStationRide()
                    .andExpect(status().isNotFound())
            ;
        }

        private ResultActions callStartStationRide() throws Exception {
            return mockMvc.perform(post("/api/rides/station")
                    .contentType(APPLICATION_JSON)
                    .content("{\n" +
                            "  \"userId\": 123,\n" +
                            "  \"stationId\": 125\n" +
                            "}"));
        }

        private OngoingStubbing<StationRideStarted> whenStartStationRide() {
            return when(rideFacade.startStationRide(userId.getValue(), stationId.getValue()));
        }

    }

    @Nested
    class EndStationRide {

        private final UserId userId = new UserId(124);
        private final LockId lockId = new LockId(1251);

        @Test
        void ok() throws Exception {
            callEndStationRide()
                    .andExpect(status().isOk())
            ;
            verifyEndStationRide();
        }

        @Test
        void stationNotFound() throws Exception {
            whenEndStationRideThrow(new StationNotFound(lockId));
            callEndStationRide()
                    .andExpect(status().isNotFound())
            ;
        }

        @Test
        void onlyStationVehicleCanBeLockedAtStation() throws Exception {
            whenEndStationRideThrow(new OnlyStationVehicleCanBeLockedAtStation());
            callEndStationRide()
                    .andExpect(status().isBadRequest())
            ;
        }

        @Test
        void noActiveRideForUser() throws Exception {
            whenEndStationRideThrow(new NoActiveRideForUser(userId));
            callEndStationRide()
                    .andExpect(status().isNotFound())
            ;
        }

        private ResultActions callEndStationRide() throws Exception {
            return mockMvc.perform(post("/api/rides/station/end")
                    .contentType(APPLICATION_JSON)
                    .content("{\n" +
                            "  \"userId\": 124,\n" +
                            "  \"lockId\": 1251\n" +
                            "}"));
        }

        private void verifyEndStationRide() {
            verify(rideFacade).endStationRide(userId.getValue(), lockId.getValue());
        }

        private void whenEndStationRideThrow(Exception exception) {
            doThrow(exception).when(rideFacade).endStationRide(userId.getValue(), lockId.getValue());
        }
    }

    @Nested
    class FreeVehicleRide {

        private final UserId userId = new UserId(123);
        private final VehicleId vehicleId = new VehicleId(214);
        private final RideId rideId = new RideId(12345);

        @Test
        void ok() throws Exception {
            whenStartFreeVehicleRide().thenReturn(rideId);
            callStartFreeVehicleRide()
                    .andExpect(status().isCreated())
                    .andExpect(header().string(LOCATION, "/api/rides/12345"))
            ;
        }

        @Test
        void noSubscription() throws Exception {
            whenStartFreeVehicleRide().thenThrow(new UserHasNoActiveSubscription());
            callStartFreeVehicleRide()
                    .andExpect(status().isForbidden())
            ;
        }

        @Test
        void hasAnotherActiveRide() throws Exception {
            whenStartFreeVehicleRide().thenThrow(new OnlyOneActiveRideAllowed());
            callStartFreeVehicleRide()
                    .andExpect(status().isForbidden())
            ;
        }

        @Test
        void vehicleNotFound() throws Exception {
            whenStartFreeVehicleRide().thenThrow(new VehicleNotFound(vehicleId));
            callStartFreeVehicleRide()
                    .andExpect(status().isNotFound())
            ;
        }

        private ResultActions callStartFreeVehicleRide() throws Exception {
            return mockMvc.perform(post("/api/rides/free")
                    .contentType(APPLICATION_JSON)
                    .content("{\n" +
                            "  \"userId\": 123,\n" +
                            "  \"vehicleId\": 214\n" +
                            "}"));
        }

        private OngoingStubbing<RideId> whenStartFreeVehicleRide() {
            return when(rideFacade.startFreeVehicleRide(userId.getValue(), vehicleId.getValue()));
        }

    }

    @Nested
    class EndFreeVehicleRide {

        private final UserId userId = new UserId(124);
        private final VehicleId vehicleId = new VehicleId(214);

        @Test
        void ok() throws Exception {
            callEndFreeVehicleRide()
                    .andExpect(status().isOk())
            ;
            verifyEndFreeVehicleRide();
        }

        @Test
        void vehicleNotFound() throws Exception {
            whenEndFreeVehicleRideThrow(new VehicleNotFound(vehicleId));
            callEndFreeVehicleRide()
                    .andExpect(status().isNotFound())
            ;
        }

        @Test
        void onlyFreeVehiclesCanBeLockedAnywhere() throws Exception {
            whenEndFreeVehicleRideThrow(new OnlyFreeRideVehicleCanBeLockedAnywhere());
            callEndFreeVehicleRide()
                    .andExpect(status().isBadRequest())
            ;
        }

        @Test
        void noActiveRideForUser() throws Exception {
            whenEndFreeVehicleRideThrow(new NoActiveRideForUser(userId));
            callEndFreeVehicleRide()
                    .andExpect(status().isNotFound())
            ;
        }

        private ResultActions callEndFreeVehicleRide() throws Exception {
            return mockMvc.perform(post("/api/rides/free/end")
                    .contentType(APPLICATION_JSON)
                    .content("{\n" +
                            "  \"userId\": 124,\n" +
                            "  \"vehicleId\": 214\n" +
                            "}"));
        }

        private void verifyEndFreeVehicleRide() {
            verify(rideFacade).endFreeVehicleRide(userId.getValue(), vehicleId.getValue());
        }

        private void whenEndFreeVehicleRideThrow(Exception exception) {
            doThrow(exception).when(rideFacade).endFreeVehicleRide(userId.getValue(), vehicleId.getValue());
        }
    }
}