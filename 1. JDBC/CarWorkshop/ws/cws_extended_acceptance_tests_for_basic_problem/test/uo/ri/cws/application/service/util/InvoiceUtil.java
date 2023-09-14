package uo.ri.cws.application.service.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import uo.ri.cws.application.persistence.invoice.InvoiceGateway.InvoiceDALDto;
import uo.ri.cws.application.service.util.sql.AddInvoiceSqlUnitOfWork;
import uo.ri.cws.application.service.util.sql.FindInvoiceByNumberSqlUnitOfWork;
import uo.ri.cws.application.service.util.sql.FindInvoiceSqlUnitOfWork;

public class InvoiceUtil {

	private InvoiceDALDto result = createDefaultInvoiceRecord();

	public InvoiceDALDto get() {
		return result;
	}
	
	private LocalDate randomDate() {
		LocalDate dateBefore = LocalDate.parse("2020-01-01");
		LocalDate dateAfter = LocalDate.now();
		long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
		LocalDate randomDate = dateBefore.plusDays(
			        ThreadLocalRandom.current().nextLong(noOfDaysBetween +1));
		return randomDate;
	}

	private InvoiceDALDto createDefaultInvoiceRecord() {
		InvoiceDALDto res = new InvoiceDALDto();

		res.id = UUID.randomUUID().toString();
		res.version = 1L;
		res.date = randomDate();
		res.state = "NOT_YET_PAID";
		res.amount = new Random().nextDouble();
		res.vat = 21.0;
		return res;
	}
	
	public InvoiceUtil withState(String state) {
		result.state =  state;
		return this;
	}
	
	public InvoiceUtil withAmount(double amount) {
		result.amount= amount * (1 + result.vat/100);
		return this;
	}
	
	public InvoiceUtil withDate(String date) {
		result.date = LocalDate.parse(date);
		return this;
	}

	public InvoiceUtil withVat(double arg) {
		result.vat = arg;
		return this;
	}

	public InvoiceUtil withNumber(long arg) {
		result.number = arg;
		return this;
	}

	public InvoiceUtil register() {
		new AddInvoiceSqlUnitOfWork(result).execute();
		return this;
	}
	
	public InvoiceUtil find (String id) {
		FindInvoiceSqlUnitOfWork find = new FindInvoiceSqlUnitOfWork(id);
		find.execute();
		result = find.get();
		return this;
	}

	public InvoiceUtil findByNumber (String number) {
		FindInvoiceByNumberSqlUnitOfWork find = new FindInvoiceByNumberSqlUnitOfWork(number);
		find.execute();
		result = find.get();
		return this;
	}
}
