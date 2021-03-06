/*
 * Copyright 2021-2021 the original author or authors.
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
package example.springdata.mongodb.unwrapping;

import java.util.Objects;

/**
 * Value object capturing the {@code email} address.
 *
 * @author Christoph Strobl
 */
public class Email { // might as well be a record type in more recent java versions

	private final String email;

	public Email(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Email email1 = (Email) o;
		return Objects.equals(email, email1.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}
}
