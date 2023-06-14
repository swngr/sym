package org.fw.runner;

import org.fw.testcases.HomeScreenTest;
import org.fw.testcases.LoginTest;
import org.fw.testcases.PracticeFlowTest;
import org.fw.testcases.SecureMessagingTest;
import org.fw.testcases.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTest.class,
        HomeScreenTest.class,
        SecureMessagingTest.class,
        PracticeFlowTest.class,
        CheckInCheckOutTest.class,
        SearchTabTest.class,
        ReportsTest.class
})
public class RegressionTestSuite {
  // This class should be empty

}