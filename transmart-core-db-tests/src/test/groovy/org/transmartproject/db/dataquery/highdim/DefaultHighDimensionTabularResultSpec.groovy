/*
 * Copyright © 2013-2014 The Hyve B.V.
 *
 * This file is part of transmart-core-db.
 *
 * Transmart-core-db is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * transmart-core-db.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.transmartproject.db.dataquery.highdim

import org.hibernate.ScrollableResults
import spock.lang.Specification

import static org.hamcrest.Matchers.*

class DefaultHighDimensionTabularResultSpec extends Specification {

    void testEquals() {
        def a = new DefaultHighDimensionTabularResult(closeSession: false),
            b = new DefaultHighDimensionTabularResult(closeSession: false)

        /* to avoid warning */
        a.close()
        b.close()

        expect:
        a is(not(equalTo(b)))
    }

    void testEmptyResultSet() {
        ScrollableResults mockedResults = Mock(ScrollableResults)
        def testee = new DefaultHighDimensionTabularResult(
                results: mockedResults,
                closeSession: false)

        mockedResults.next() >> false
        mockedResults.get() >> null

        when:
        testee.iterator().next()
        then:
        thrown(NoSuchElementException)

        cleanup:
        testee.close()
    }

}
