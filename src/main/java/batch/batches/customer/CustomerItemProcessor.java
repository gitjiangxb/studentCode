package batch.batches.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class CustomerItemProcessor implements ItemProcessor<Customer, Customer> {

	private static final Logger log = LoggerFactory.getLogger(CustomerItemProcessor.class);

	@Override
	public Customer process(final Customer person) throws Exception {
		return person;
	}

}
