
package org.test.validators;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Pattern;
import ch.kerbtier.phalidator.rt.Util;
import ch.kerbtier.phalidator.rt.Validateable;

public class User
    implements Validateable
{

    private Map<String, String> model;

    public User(Map<String, String> model) {
        this.model = model;
    }

    public Collection<String> getInvalidKeys() {
        Collection<String> invalidKeys = new ArrayList<String>();
        if (!isNameValid()) {
            invalidKeys.add("name");
        }
        if (!isLastNameValid()) {
            invalidKeys.add("lastName");
        }
        if (!isEmailValid()) {
            invalidKeys.add("email");
        }
        if (!isCityValid()) {
            invalidKeys.add("city");
        }
        if (!isZipValid()) {
            invalidKeys.add("zip");
        }
        return invalidKeys;
    }

    public boolean isNameValid() {
        return (new BigDecimal(this.model.get("name").length()).compareTo(new BigDecimal("0"))> 0);
    }

    public boolean isLastNameValid() {
        return (new BigDecimal(this.model.get("lastName").length()).compareTo(new BigDecimal("0"))> 0);
    }

    public boolean isEmailValid() {
        return Util.matches(this.model.get("email"), Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,8}"));
    }

    public boolean isCityValid() {
        return (new BigDecimal(this.model.get("city").length()).compareTo(new BigDecimal("1"))> 0);
    }

    public boolean isZipValid() {
        return (Util.in(new BigDecimal(this.model.get("zip").length()), Util.range(new BigDecimal("4"), new BigDecimal("6")))&&Util.in(new HashSet(Arrays.asList(this.model.get("zip").split("(?!^)"))), "0123456789"));
    }

    public boolean isValid() {
        return ((((isNameValid()&&isLastNameValid())&&isEmailValid())&&isCityValid())&&isZipValid());
    }

}
