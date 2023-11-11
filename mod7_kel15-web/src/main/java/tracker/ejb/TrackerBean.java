package tracker.ejb;

import jakarta.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class TrackerBean implements TrackerBeanLocal {
    private double total = 0;
    private int count = 0;
    private List<Double> bilanganGenap = new ArrayList<>();
    private List<Double> bilanganGanjil = new ArrayList<>();

    @Override
    public double add(double value) {
        total += value;
        count += 1;

        if (value % 2 == 0) {
            bilanganGenap.add(value);
        } else {
            bilanganGanjil.add(value);
        }

        return total;
    }

    @Override
    public double average() {
        return total / count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getTotal() {
        return total;
    }

    @Override
    public List<Double> getBilanganGenap() {
        return bilanganGenap;
    }

    @Override
    public List<Double> getBilanganGanjil() {
        return bilanganGanjil;
    }
}
