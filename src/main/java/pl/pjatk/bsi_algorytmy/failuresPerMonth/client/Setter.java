package pl.pjatk.bsi_algorytmy.failuresPerMonth.client;

import pl.pjatk.bsi_algorytmy.chipset.model.TestingStatistic;

/**
 *California Instruments, Inc., produces 3,000 computer chips per day. Three hundred are tested for a period of 500 operating hours each.
 *During the test, six failed: two after 50 hours, two at 100 hours, one at 300 hours, and one at 400 hours.
 *Find FR(%) and FR(N).
 */

/**
 *FR(%) = failures per number tested = 6/300 = 0.02 = 2%
 *FR(N) = failures per operating time:
 *Total time = 300 * 500 = 150,000 hours
 *Downtime = 2(450) + 2(400) + 1(200) + 1(100) = 2,000 hours
 *Operating time = Total time – Downtime = 150,000 – 2,000 = 148,000
 *Therefore: FR(N) = 6/148,000 = 0.0000405 failures/hour
 *MTBF = 1/FR(N) = 24,691 hours
 */

public class Setter {
    public void setter(){
        TestingStatistic testingStatistic = new TestingStatistic();

        testingStatistic.setFrPerTime(0.0000405);
        testingStatistic.setFrPerTest(0.02);
        testingStatistic.setOperatingTime(148000);
        testingStatistic.setMTBF(24691);
        testingStatistic.setTotalTime(150000);
        testingStatistic.setDownTime(2000);
    }
}