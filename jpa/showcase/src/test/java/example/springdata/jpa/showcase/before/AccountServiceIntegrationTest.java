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
package example.springdata.jpa.showcase.before;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import example.springdata.jpa.showcase.AbstractShowcaseTest;
import example.springdata.jpa.showcase.core.Account;
import example.springdata.jpa.showcase.core.Customer;

/**
 * Integration test for {@link AccountService}.
 *
 * @author Oliver Gierke
 * @author Divya Srivastava
 * @author Jens Schauder
 */
public class AccountServiceIntegrationTest extends AbstractShowcaseTest {

	@Autowired AccountService accountService;
	@Autowired CustomerService customerService;

	@Test
	public void savesAccount() {

		Account account = accountService.save(new Account());
		assertThat(account.getId(), is(notNullValue()));
	}

	@Test
	public void testname() throws Exception {

		Customer customer = customerService.findById(1L);

		List<Account> accounts = accountService.findByCustomer(customer);

		assertThat(accounts, is(not(empty())));
		assertThat(accounts.get(0).getCustomer(), is(customer));
	}
}
