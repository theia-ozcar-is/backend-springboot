/*
 * koppen-climate
 * openAPI definition for koppen-climate API. The API is queried using a location and return the Koppen climate corresponding to the location. Koppen climates come from Hylke E. Beck et al. 2018. https://doi.org/10.1038/sdata.2018.214. The dataset used is Beck_KG_V1_present_conf_0p0083.tif that represents the present-day (1980–2016) Köppen-Geiger climate classification at a spacial resolution of 0.0083°.
 *
 * The version of the OpenAPI document: 0.1
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package fr.theialand.insitu.dataportal.api.koppenclimate.handler;

import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class RFC3339DateFormat extends DateFormat {
  private static final long serialVersionUID = 1L;
  private static final TimeZone TIMEZONE_Z = TimeZone.getTimeZone("UTC");

  private final StdDateFormat fmt = new StdDateFormat()
          .withTimeZone(TIMEZONE_Z)
          .withColonInTimeZone(true);

  public RFC3339DateFormat() {
    this.calendar = new GregorianCalendar();
  }

  @Override
  public Date parse(String source) {
    return parse(source, new ParsePosition(0));
  }

  @Override
  public Date parse(String source, ParsePosition pos) {
    return fmt.parse(source, pos);
  }

  @Override
  public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
    return fmt.format(date, toAppendTo, fieldPosition);
  }

  @Override
  public Object clone() {
    return super.clone();
  }
}