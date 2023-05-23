package br.com.pgtel.pgtelbackend.modules.stock.infra.validators

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException
import kotlin.reflect.KClass


@Target(*[AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.TYPE_PARAMETER])
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [EnumNamePatternValidator::class])
annotation class EnumNamePattern(
    val regexp: String,
    val message: String = "must match \"{regexp}\"",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class EnumNamePatternValidator : ConstraintValidator<EnumNamePattern, Enum<*>?> {
    lateinit var pattern: Pattern
    override fun initialize(annotation: EnumNamePattern) {
        pattern = try {
            Pattern.compile(annotation.regexp)
        } catch (e: PatternSyntaxException) {
            throw IllegalArgumentException("Given regex is invalid", e)
        }
    }

    override fun isValid(value: Enum<*>?, context: ConstraintValidatorContext): Boolean {
        if (value == null) {
            return true
        }
        val m: Matcher = pattern.matcher(value.name)
        return m.matches()
    }
}