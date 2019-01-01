package com.thecodeslinger

import spock.lang.Specification

class TestBahnControlCodeSpec extends Specification {

    def "null input"() {
        expect:
        !BahnControlCode.isValid(null)
    }

    def "empty input"() {
        expect:
        !BahnControlCode.isValid("")
    }

    def "number without control code"() {
        expect:
        !BahnControlCode.isValid("1234567")
    }

    def "number too short"() {
        expect:
        !BahnControlCode.isValid("1234-4")
    }

    def "invalid code"() {
        expect:
        !BahnControlCode.isValid("2341932-2")
    }

    def "valid code"() {
        expect:
        BahnControlCode.isValid("2341932-8")
    }

    def "valid code edge case even sum of digits"() {
        expect:
        BahnControlCode.isValid("1341932-0")
    }
}
