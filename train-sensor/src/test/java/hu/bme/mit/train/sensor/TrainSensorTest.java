package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainUser user;
    TrainSensorImpl ts;

    TrainController tc;

    @Before
    public void before() {
        tc = mock(TrainController.class,withSettings().verboseLogging());
        user = mock(TrainUser.class,withSettings().verboseLogging());
        ts = new TrainSensorImpl(tc, user);
    }

    @Test
    public void overrideSpeedLimit_limitAboveMax_alarm(){
        ts.overrideSpeedLimit(501);
        Mockito.verify(user, Mockito.times(1)).setAlarmState(true);
    }

    @Test
    public void overrideSpeedLimit_limitUnderZero_alarm() {
        ts.overrideSpeedLimit(-1);
        Mockito.verify(user, Mockito.times(1)).setAlarmState(true);
    }

    @Test
    public void overrideSpeedLimit_noAlarm(){
        ts.overrideSpeedLimit(499);
        Mockito.verify(user, Mockito.times(1)).setAlarmState(false);
    }

    @Test
    public void overrideSpeedLimit_SuddenSpeedDrop_alarm(){
        tc.setJoystickPosition(150);
        tc.followSpeed();
        when(tc.getReferenceSpeed()).thenReturn(150);
        ts.overrideSpeedLimit(4);
        Mockito.verify(user, Mockito.times(1)).setAlarmState(true);
    }
}