/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdomain;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.DoubleStream;

/**
 *
 * @author ryanarchy
 */
public class HourlyEmployee implements Employee
{
    HashMap<Short, FiscalYear> fyCalendar = new HashMap<>();  //hash map that has year, FiscalYear class pairs.  Year will signify the fiscal year represented for lookup purposes.
    
    String name;
    Double rate;
    Calendar cal = Calendar.getInstance();
    /**
     * Basic constructor class
     * @param name This value holds the employee's full name
     * @param rate this value holds the employee's pay rate in dollars per hour
     */
    public HourlyEmployee(String name, Double rate)
    {
        this.name = name;
        this.rate = rate;
        cal.setTime(new Date());
        this.addFiscalYear(cal.get(Calendar.YEAR));
    }
    /**
     * This is a basic constructor class that will also create a starting year
     * for the employee.
     * @param name This value holds the employee's full name
     * @param rate this value holds the employee's pay rate in dollars per hour
     * @param year This is the fiscal year that will be created on the employee
     */
    public HourlyEmployee(String name, Double rate, int year)
    {
        this.name = name;
        this.rate = rate;
        this.addFiscalYear((short) year);
    }
    
    /**
     * Setter for employee name.
     * @param name Employee's full name.
     */
    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Getter for employee's name.
     * @return Employee's full name.
     */
    @Override
    public String getName()
    {
        return this.name;
    }

    /**
     * Setter for the employee's pay rate.
     * @param rate Employee's pay rate in dollars per hour.
     */
    @Override
    public void setPayRate(double rate)
    {
        this.rate = rate;
    }

    /**
     * Getter for the employee's pay rate.
     * @return Employee's pay rate in dollars per hour.
     */
    @Override
    public double getPayRate()
    {        
        return this.rate;
    }

    /**
     * Getter for a specific pay period of this employee.  This method works by
     * receiving a year and pay period as input and then searching fyCalendar
     * for a FiscalCalendar that has a key matching the provided year.  Once
     * the correct FiscalCalendar has been found, we poll that object for the
     * requested pay period and return it.
     * @param year Short value holding the year of the pay peroid we wish to 
     * locate
     * @param period Short value holding the pay period we wish to return,
     * this should expect 1-26 or possibly 27
     * @return This is a key, value HashMap that holds Dates as keys and the
     * number of hours worked as double values.  Should contain either 26 or 27
     * date, hour pairs
     */
    @Override
    public HashMap<Date, Double> getPayPeriod(int year, int period) 
    {
        FiscalYear y = fyCalendar.get((short) year);
        return y.getPeriod((short) period);
    }

    /**
     * Test to see if this employee has payroll data for the requested year
     * @param year Year we wish to text the existence
     * @return true or false
     */
    @Override
    public boolean hasYear(int year)
    {
        return fyCalendar.containsKey((short) year);
    }

    /**
     * This function will return the number of hours worked for a specific date
     * @param date The date we wish to know the total number of hours worked.
     * @return Double value containing the number of hours worked for the 
     * provided date.
     */
    @Override
    public double getHours(Date date)
    {
        cal.setTime(date);
        FiscalYear y = fyCalendar.get((short) cal.get(Calendar.YEAR));
        return y.getDateHours(date);
    }

    /**
     * This is the agregate sum of the hours worked for the provided year.
     * @param year Short value containing the year that we wish to get the total
     * number of hours worked in.
     * @return A double value containing the number of hours worked in the year
     * provided.
     */
    @Override
    public double getTotalHours(int year)
    {
        return fyCalendar.get((short) year).getTotalHours();
    }

    @Override
    public void setHoursWorked(Date date, double hours)
    {
        cal.setTime(date);
        fyCalendar.get((short) cal.get(Calendar.YEAR)).setDateHours(date, hours);
    }

    @Override
    public double getFederalTax(int year)
    {
        return this.getGrossPay(year) * TaxRates.FEDERAL_TAX_RATE;
    }

    @Override
    public double getStateTax(int year)
    {
        return this.getGrossPay(year) * TaxRates.STATE_TAX_RATE;
    }

    @Override
    public double getMedicareTax(int year)
    {
        return this.getGrossPay(year) * TaxRates.MEDICARE_TAX_RATE;
    }

    @Override
    public double getSocialSecurityTax(int year)
    {
        double ssTax = this.getGrossPay(year) * TaxRates.SOCIAL_SECURITY_RATE;
        
        if (ssTax > TaxRates.SOCIAL_SECURITY_MAX)
        {
            ssTax = TaxRates.SOCIAL_SECURITY_MAX;
        }
        
        return ssTax;
    }

    @Override
    public double getDeductions(int year)
    {
        double[] deductions = { this.getFederalTax(year),
                                this.getStateTax(year),
                                this.getMedicareTax(year),
                                this.getSocialSecurityTax(year)};
        
        return DoubleStream.of(deductions).sum();
        
    }

    @Override
    public double getGrossPay(int year)
    {
        FiscalYear fy = fyCalendar.get((short) year);
        return (fy.getTotalHours() * this.getPayRate());
    }

    @Override
    public double getNetPay(int year)
    {
        return this.getGrossPay(year) - this.getDeductions(year);
    }

    @Override
    public FiscalYear getFiscalYear(int year)
    {
        return fyCalendar.get((short) year);
    }

    @Override
    public final void addFiscalYear(int year)
    {
        if (!this.hasYear(year))
        {
            fyCalendar.put((short)year, new FiscalYear((short) year));
        }
        else
        {
            throw new IllegalArgumentException("Requested year " + year + 
                    " already exists on Employee.");
        }
    }

    @Override
    public void addFiscalYearBefore()
    {
        short min = Collections.min(this.fyCalendar.keySet());
        short yearAfter =  (short) (min + 1);
        this.addFiscalYear(yearAfter);
        
    }

    @Override
    public void addFiscalYearAfter()
    {
        short max = Collections.max(this.fyCalendar.keySet());
        short yearAfter =  (short) (max + 1);
        this.addFiscalYear(yearAfter);
    }

    @Override
    public int[] getFiscalYears()
    {
        return this.fyCalendar.keySet().stream().mapToInt(i->i).toArray();
    }
    
    
    
}
