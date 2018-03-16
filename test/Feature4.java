import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import tasproject.Badge;
import tasproject.Punch;
import tasproject.Shift;
import tasproject.TASDatabase;
import tasproject.TASLogic;

public class Feature4 {
    
    private TASDatabase db;
    
    @Before
    public void setup() throws SQLException, InstantiationException, IllegalAccessException {
        db = new TASDatabase();
    }
    
    @Test
    public void testMinutesAccruedShift1Weekday() throws SQLException, InstantiationException, IllegalAccessException {
		
        /* Get Punch */
        
        Punch p = db.getPunch(3634);
        Badge b = db.getBadge(p.getBadgeid());
        Shift s = db.getShift(b);
        
        ArrayList<Punch> dailypunchlist = db.getDailyPunchList(b, p.getOriginaltimestamp());
		
        /* Compute Pay Period Total */
        
        int m = TASLogic.calculateTotalMinutes(dailypunchlist, s);
		
        /* Compare to Expected Value */
        
        assertEquals(480, m);
        
    }
    
    @Test
    public void testMinutesAccruedShift1Weekend() throws SQLException, InstantiationException, IllegalAccessException {
		
        /* Get Punch */
        
        Punch p = db.getPunch(1087);
        Badge b = db.getBadge(p.getBadgeid());
        Shift s = db.getShift(b);
        
        ArrayList<Punch> dailypunchlist = db.getDailyPunchList(b, p.getOriginaltimestamp());
		
        /* Compute Pay Period Total */
        
        int m = TASLogic.calculateTotalMinutes(dailypunchlist, s);
		
        /* Compare to Expected Value */
        
        assertEquals(m, 360);
        
    }

    @Test
    public void testMinutesAccruedShift2Weekday() throws SQLException, InstantiationException, IllegalAccessException {
		
        /* Get Punch */
        
        Punch p = db.getPunch(4943);
        Badge b = db.getBadge(p.getBadgeid());
        Shift s = db.getShift(b);
        
        ArrayList<Punch> dailypunchlist = db.getDailyPunchList(b, p.getOriginaltimestamp());
        
        /* Compute Pay Period Total */
        
        int m = TASLogic.calculateTotalMinutes(dailypunchlist, s);
		
        /* Compare to Expected Value */
        
        assertEquals(540, m);
        
    }
    
}