/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ryanarchy
 */
public class FiscalYear
{
    List<HashMap<Date, Double>> payPeriods = 
            new ArrayList<HashMap<Date, Double>>();
    short year;
    
    public FiscalYear(short year)
    {
        
    }
    
    public FiscalYear(String year)
    {
        
    }
    
    public FiscalYear(Date year)
    {
        
    }
    
    public HashMap<Date,Double> getPeriod(short period)
    {
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.       
    }
    
    public void setPeriodHours(char period, double[] hours)
    {
        
    }
    
    public void setDateHours(Date date, double hours)
    {
        
    }
    public double getDateHours(Date date)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Date getYear()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.        
    }
    
    public double getPeriodHours(short period)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public double getPeriodOvertime(short period)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public double getPeriodDoubletime(short period)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public double getTotalHours()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public double getDoubletimeHours()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public double getOvertimeHours()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
