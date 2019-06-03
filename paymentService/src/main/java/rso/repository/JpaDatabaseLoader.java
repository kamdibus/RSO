//package rso.repository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import rso.model.Offer;
//import rso.model.Payment;
//import rso.model.StatusType;
//
//import java.util.Date;
//
//@Component
//public class JpaDatabaseLoader implements CommandLineRunner {
//
//    private final PaymentRepository paymentRepository;
//    private final OfferRepository offerRepository;
//
//    @Autowired
//    public JpaDatabaseLoader(PaymentRepository paymentRepository, OfferRepository offerRepository) {
//        this.paymentRepository = paymentRepository;
//        this.offerRepository = offerRepository;
//    }
//
//    @Override
//    public void run(String... strings) throws Exception {
//        this.paymentRepository.save(new Payment(new Date() , StatusType.accepted));
//        Payment payment = this.paymentRepository.findById((long) 1).get();
//        this.offerRepository.save(new Offer(new Date(), new Date(), 13131221, 1, 12.112f, StatusType.accepted, new Payment(new Date() ,StatusType.pending)));
//        this.offerRepository.save(new Offer(new Date(), new Date(), 13131221, 2, 12.112f, StatusType.accepted, new Payment(new Date() ,StatusType.pending)));
//        this.offerRepository.save(new Offer(new Date(), new Date(), 13131221, 3, 12.112f, StatusType.accepted, new Payment(new Date() ,StatusType.pending)));
//        this.offerRepository.save(new Offer(new Date(), new Date(), 4, 4, 12.112f, StatusType.accepted, new Payment(new Date() ,StatusType.pending)));
//        this.offerRepository.save(new Offer(new Date(), new Date(), 5, 5, 12.112f, StatusType.accepted, new Payment(new Date() ,StatusType.pending)));
//    }
//}
