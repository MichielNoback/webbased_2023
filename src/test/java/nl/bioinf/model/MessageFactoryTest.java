package nl.bioinf.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageFactoryTest {

    @Test
    void giveMessage() {
        MessageFactory mf = new MessageFactory();
        System.out.println(mf.giveMessage());
    }
}