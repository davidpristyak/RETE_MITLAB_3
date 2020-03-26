package hu.bme.mit.train.tachograph;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.system.TrainSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TachographTest {

    TrainSystem trainSystem;
    TrainController controller;
    TrainSensor sensor;
    TrainUser user;
    Tachograph tachograph2;

    @Before
    public void before() {
        this.trainSystem = new TrainSystem();
        controller = trainSystem.getController();
        user = trainSystem.getUser();
        sensor = trainSystem.getSensor();
        tachograph2 = new Tachograph(controller, user);
    }

    @Test
    public void isEmptyTest() {
        Assert.assertEquals(true, tachograph2.isTableEmpty());
        user.overrideJoystickPosition(40);
        this.tachograph2.saveData();

        user.overrideJoystickPosition(40);
        Assert.assertEquals(false, tachograph2.isTableEmpty());
    }
}
