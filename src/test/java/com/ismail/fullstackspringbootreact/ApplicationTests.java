package com.ismail.fullstackspringbootreact;

import com.ismail.fullstackspringbootreact.util.EmailValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ApplicationTests
{
    private final EmailValidator underTest = new EmailValidator();

    @Test
    public void itShouldValidateCorrectEmail()
    {
        Assertions.assertThat(underTest.test("hello@gmail.com")).isTrue();
    }

    @Test
    public void itShouldValidateIncorrectEmail()
    {
        Assertions.assertThat(underTest.test("hellogmail.com")).isFalse();
    }

    @Test
    public void itShouldValidateIncorrectEmailWithoutDot()
    {
        Assertions.assertThat(underTest.test("hello@gmail")).isFalse();
    }
}
