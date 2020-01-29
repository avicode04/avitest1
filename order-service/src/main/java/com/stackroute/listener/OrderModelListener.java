//package com.stackroute.listener;
//
//import com.stackroute.domain.Order;
//import com.stackroute.service.SequenceGeneratorService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
//import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
//import org.springframework.stereotype.Component;
//
//@Component
//public class OrderModelListener extends AbstractMongoEventListener {
//
//    private SequenceGeneratorService sequenceGeneratorService;
//
//    @Autowired
//    public OrderModelListener(SequenceGeneratorService sequenceGeneratorService) {
//        this.sequenceGeneratorService = sequenceGeneratorService;
//    }
//
//    @Override
//    public void onBeforeConvert(BeforeConvertEvent event) {
//        if (event.getSource().getId < 1) {
//            event.getSource().setOrderId(sequenceGeneratorService.generateSequence(Order.SEQUENCE_NAME));
//        }
//    }
//}
