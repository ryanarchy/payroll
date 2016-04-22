/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdomain;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author ryanarchy
 */
public class HourlyEmployee implements Employee
{
    HashMap<Short, FiscalYear> fyCalendar;  //hash map that has year, FiscalYear class pairs.  Year will signify the fiscal year represented for lookup purposes.
    
    String name;
    Double rate;
    Calendar cal = Calendar.getInstance();
    
    
    
    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public void setPayRate(double rate)
    {
        this.rate = rate;
    }

    @Override
    public double getPayRate()
    {        
        return this.rate;
    }

    @Override
    //return a pay period in the form of a hashmap of a (date, hour) pairs
    public HashMap<Date, Double> getPayPeriod(short year, short period) 
    {
        FiscalYear y = fyCalendar.get(year);
        return y.getPeriod(period);
    }

    @Override
    public boolean hasYear(short year)
    {
        return fyCalendar.containsKey(year);
    }

    @Override
    public double getHours(Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        FiscalYear y = fyCalendar.get((short) cal.get(Calendar.YEAR));
        return y.getDateHours(date);
    }

    @Override
    public double getTotalHours(short year)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHoursWorked(Date date, double hours)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getFederalTax(short year)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getStateTax(short year)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getMedicareTax(short year)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getSocialSecurityTax(short year)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getDeductions(short year)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getGrossPay(short year)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getNetPay(short year)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FiscalYear getFiscalYear(short year)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
