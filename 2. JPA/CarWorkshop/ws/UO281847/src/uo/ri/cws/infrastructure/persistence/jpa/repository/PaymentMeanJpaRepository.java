package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.repository.PaymentMeanRepository;
import uo.ri.cws.domain.CreditCard;
import uo.ri.cws.domain.PaymentMean;
import uo.ri.cws.domain.Voucher;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class PaymentMeanJpaRepository extends BaseJpaRepository<PaymentMean> implements PaymentMeanRepository {

	@Override
	public List<PaymentMean> findPaymentMeansByClientId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentMean> findPaymentMeansByInvoiceId(String id) {
		return Jpa.getManager()
				.createNamedQuery("PaymentMean.findByInvoiceId", PaymentMean.class)
				.setParameter(1, id)
				.getResultList();
	}

	@Override
	public List<PaymentMean> findByClientId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] findAggregateVoucherDataByClientId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CreditCard> findCreditCardByNumber(String pan) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Voucher> findVouchersByClientId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Voucher> findVoucherByCode(String code) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}
