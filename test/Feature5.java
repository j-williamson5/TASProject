import java.sql.SQLException;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.*;
import tasproject.Badge;
import tasproject.Punch;
import tasproject.TASDatabase;
import tasproject.TASLogic;

public class Feature5 {
    
    private TASDatabase db;
    
    @Before
    public void setup() throws SQLException, InstantiationException, IllegalAccessException {
        db = new TASDatabase();
    }
    
    @Test
    public void testJSONShift1Weekday() throws SQLException, InstantiationException, IllegalAccessException {
        
        /* Expected JSON Data */
        
        String expectedJSON = "[{\"eventdata\":\"Shift Start\",\"originaltimestamp\":\"1504871435000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1504872000000\",\"eventtypeid\":\"1\",\"terminalid\":\"104\",\"id\":\"3634\"},{\"eventdata\":\"Lunch Start\",\"originaltimestamp\":\"1504890234000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1504890000000\",\"eventtypeid\":\"0\",\"terminalid\":\"104\",\"id\":\"3687\"},{\"eventdata\":\"Lunch Stop\",\"originaltimestamp\":\"1504891421000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1504891800000\",\"eventtypeid\":\"1\",\"terminalid\":\"104\",\"id\":\"3688\"},{\"eventdata\":\"Shift Stop\",\"originaltimestamp\":\"1504902853000\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"1504902600000\",\"eventtypeid\":\"0\",\"terminalid\":\"104\",\"id\":\"3716\"}]";
        
        ArrayList<HashMap<String, String>> expected = (ArrayList)JSONValue.parse(expectedJSON);
		
        /* Get Punch */
        
        Punch p = db.getPunch(3634);
        Badge b = db.getBadge(p.getBadgeid());
		
        /* Get Daily Punch List */
        
        String actualJSON = TASLogic.getPunchListAsJSON(db.getDailyPunchList(b, p.getOriginaltimestamp()));
        
        ArrayList<HashMap<String, String>> actual = (ArrayList)JSONValue.parse(actualJSON);
		
        /* Compare to Expected Value */
        
        assertEquals(expected, actual);
        
    }
    
    @Test
    public void testJSONShift1Weekend() throws SQLException, InstantiationException, IllegalAccessException {
        
        /* Expected JSON Data */
        
        String expectedJSON = "[{\"eventdata\":\"Interval Round\",\"originaltimestamp\":\"1502535298000\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"1502535600000\",\"eventtypeid\":\"1\",\"terminalid\":\"105\",\"id\":\"1087\"},{\"eventdata\":\"Interval Round\",\"originaltimestamp\":\"1502557442000\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"1502557200000\",\"eventtypeid\":\"0\",\"terminalid\":\"105\",\"id\":\"1162\"}]";
        
        ArrayList<HashMap<String, String>> expected = (ArrayList)JSONValue.parse(expectedJSON);
		
        /* Get Punch */
        
        Punch p = db.getPunch(1087);
        Badge b = db.getBadge(p.getBadgeid());
		
        /* Get Daily Punch List */
        
        String actualJSON = TASLogic.getPunchListAsJSON(db.getDailyPunchList(b, p.getOriginaltimestamp()));
        
        ArrayList<HashMap<String, String>> actual = (ArrayList)JSONValue.parse(actualJSON);
		
        /* Compare to Expected Value */
        
        assertEquals(expected, actual);
        
    }
    
    @Test
    public void testJSONShift2Weekday() throws SQLException, InstantiationException, IllegalAccessException {
        
        /* Expected JSON Data */
        
        String expectedJSON = "[{\"eventdata\":\"Shift Start\",\"originaltimestamp\":\"1505840373000\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"1505840400000\",\"eventtypeid\":\"1\",\"terminalid\":\"104\",\"id\":\"4943\"},{\"eventdata\":\"None\",\"originaltimestamp\":\"1505874627000\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"1505874627000\",\"eventtypeid\":\"0\",\"terminalid\":\"104\",\"id\":\"5004\"}]";
        
        ArrayList<HashMap<String, String>> expected = (ArrayList)JSONValue.parse(expectedJSON);
		
        /* Get Punch */
        
        Punch p = db.getPunch(4943);
        Badge b = db.getBadge(p.getBadgeid());
		
        /* Get Daily Punch List */
        
        String actualJSON = TASLogic.getPunchListAsJSON(db.getDailyPunchList(b, p.getOriginaltimestamp()));
        
        ArrayList<HashMap<String, String>> actual = (ArrayList)JSONValue.parse(actualJSON);
		
        /* Compare to Expected Value */
        
        assertEquals(expected, actual);
        
    }
    
}