package tracker.ejb;

import jakarta.ejb.Local;
import java.util.List;

@Local
public interface TrackerBeanLocal {
    double add(double value);

    double average();

    int getCount();

    double getTotal();

    List<Double> getBilanganGenap();

    List<Double> getBilanganGanjil();
}