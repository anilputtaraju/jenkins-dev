/*
 * Copyright 2011-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.springdata.jpa.showcase;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Oliver Gierke
 * @author Divya Srivastava
 */
@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
public abstract class AbstractShowcaseTest extends AbstractTransactionalShowcaseContextTests {

	@SpringBootApplication
	static class TestConfig {}

	@BeforeTransaction
	public void setupData() throws Exception {

		deleteFromTables("account", "customer");
		executeSqlScript("classpath:import.sql", false);
	}
}
