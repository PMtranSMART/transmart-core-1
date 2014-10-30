package org.transmartproject.batch.clinical

import groovy.util.logging.Slf4j
import org.springframework.batch.item.ItemProcessor
import org.springframework.beans.factory.annotation.Value
import org.transmartproject.batch.model.Row
import org.transmartproject.batch.model.WordMapping

/**
 * ItemProcessor of Row that replaces words defined in word mappings list
 */
@Slf4j
class WordReplaceItemProcessor implements ItemProcessor<Row, Row> {

    @Value("#{clinicalJobContext.wordMappings}")
    List<WordMapping> wordMappings

    @Lazy
    private Map<String,List<Mapping>> map = calculateMappings()

    @Override
    Row process(Row item) throws Exception {
        int count = replaceWords(item)
        if (log.debugEnabled && count > 0) {
            log.debug "Replaced $count words in row ${item.index} of ${item.filename}"
        }
        item
    }

    int replaceWords(Row row) {
        List<Mapping> list = map.get(row.filename)
        int count = 0
        list.each {
            if (it.from == row.values[it.column]) {
                row.values[it.column] = it.to
                count++
            }
        }
        count
    }

    private Map<String,List<Mapping>> calculateMappings() {
        if (!wordMappings) {
            return [:]
        }

        Map<String, List<WordMapping>> map = wordMappings.groupBy { it.filename }

        map.collectEntries {
            List<Mapping> mappings = it.value.collect {
                new Mapping(from: it.originalValue, to: it.newValue, column: it.column)
            }
            [(it.key): mappings]
        }
    }

}

class Mapping {
    String from
    String to
    int column
}
