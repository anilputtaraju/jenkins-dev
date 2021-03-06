/*
 * Copyright 2021-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.springdata.elasticsearch.util;

import java.util.Optional;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * {@link ExecutionCondition} evaluating {@link EnabledOnElasticsearch @EnableOnElasticsearch}.
 *
 * @author Christoph Strobl
 * @author Prakhar Gupta
 */
class ElasticsearchAvailableCondition implements ExecutionCondition {

	@Override
	public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext extensionContext) {

		Optional<EnabledOnElasticsearch> annotation = AnnotationSupport.findAnnotation(extensionContext.getElement(),
				EnabledOnElasticsearch.class);

		return annotation.map(EnabledOnElasticsearch::url) //
				.map(ElasticsearchAvailableCondition::checkServerRunning) //
				.orElse(ConditionEvaluationResult.enabled("Enabled by default"));
	}

	private static ConditionEvaluationResult checkServerRunning(String url) {

		RestTemplate template = new RestTemplate();

		try {
			ResponseEntity<byte[]> entity = template.exchange(url, HttpMethod.HEAD, null, byte[].class);
			return ConditionEvaluationResult
					.enabled("Successfully connected to Elasticsearch server: " + entity.getStatusCode());
		} catch (RuntimeException e) {
			return ConditionEvaluationResult.disabled("Elasticsearch unavailable at " + url + "; " + e.getMessage());
		}
	}
}
