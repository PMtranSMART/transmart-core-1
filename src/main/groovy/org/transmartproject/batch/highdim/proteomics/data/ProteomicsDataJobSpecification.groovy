package org.transmartproject.batch.highdim.proteomics.data

import groovy.transform.TypeChecked
import org.transmartproject.batch.highdim.jobparams.StandardAssayParametersModule
import org.transmartproject.batch.highdim.jobparams.StandardHighDimDataParametersModule
import org.transmartproject.batch.startup.ExternalJobParametersModule
import org.transmartproject.batch.startup.JobSpecification
import org.transmartproject.batch.startup.StudyJobParametersModule

/**
 * External parameters for main proteomics data.
 */
@TypeChecked
class ProteomicsDataJobSpecification implements JobSpecification {

    final List<? extends ExternalJobParametersModule> jobParametersModules = [
            new StudyJobParametersModule(),
            new StandardAssayParametersModule('PROTEOMICS'),
            new StandardHighDimDataParametersModule()
    ]

    final Class<?> jobPath = ProteomicsDataJobConfig
}
