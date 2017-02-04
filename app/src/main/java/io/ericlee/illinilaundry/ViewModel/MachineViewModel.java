package io.ericlee.illinilaundry.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.view.View;

import io.ericlee.illinilaundry.Model.Machine;

/**
 * @author dl-eric
 */

public class MachineViewModel {
    private Context context;
    private Machine machine;

    public MachineViewModel(Context context, Machine machine) {
        this.context = context;
        this.machine = machine;
    }

    public String getName() {
        return machine.getLabel();
    }

    public String getStatus() {
        return machine.getStatus();
    }

    public String getDescription() {
        return machine.getDescription();
    }

    public String getTimeRemaining() {
        return machine.getTimeRemaining().equals("0") ? "" : machine.getTimeRemaining();
    }

    public boolean getBusy() {
        return !machine.getStatus().contains("Available");
    }

    public View.OnClickListener setAlarm() {
        if (machine.getStatus().contains("In Use") && !machine.getTimeRemaining().contains("Unknown")) {
            return new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                    i.putExtra(AlarmClock.EXTRA_MESSAGE, getName() + " : " + getDescription());
                    i.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(machine.getTimeRemaining().split(" ")[0]));
                    context.startActivity(i);
                }
            };
        }

        return null;
    }
}
