package hu.bme.mit.train.tachograph;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Tachograph {
    private static final Logger LOGGER = Logger.getLogger( Tachograph.class.getName() );
    private TrainController controller;
    private TrainUser user;

    private Table<Date, Integer, Integer> table = HashBasedTable.create();

    public Tachograph(TrainController controller, TrainUser user) {
        this.controller = controller;
        this.user = user;
    }

    public void saveData(){
        LOGGER.log(Level.ALL,"saveData"+"\tD: " + new Date()+"\tRS: " + controller.getReferenceSpeed()+"\tJP: "+ user.getJoystickPosition());
        this.table.put(new Date(), controller.getReferenceSpeed(), user.getJoystickPosition());
    }

    public Boolean isTableEmpty(){
        return this.table.isEmpty();
    }
}
