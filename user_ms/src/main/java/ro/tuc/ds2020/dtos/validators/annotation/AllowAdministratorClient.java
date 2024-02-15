package ro.tuc.ds2020.dtos.validators.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyRole('Admin', 'User')")
@Target(ElementType.METHOD)
public @interface AllowAdministratorClient {
}