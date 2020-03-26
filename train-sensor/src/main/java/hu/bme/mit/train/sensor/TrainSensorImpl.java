package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		if(this.speedLimit < 0 || this.speedLimit > 500){
			user.setAlarmState(true);
		}
		else if(this.speedLimit < controller.getReferenceSpeed()/2){
			user.setAlarmState(true);
		}
		else{
			user.setAlarmState(false);
		}
		controller.setSpeedLimit(speedLimit);

	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

}
