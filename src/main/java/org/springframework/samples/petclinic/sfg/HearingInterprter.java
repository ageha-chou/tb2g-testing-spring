package org.springframework.samples.petclinic.sfg;

import org.springframework.stereotype.Service;

@Service
public class HearingInterprter {
    private final WordProducer wordProducer;

    public HearingInterprter(WordProducer wordProducer) {
        this.wordProducer = wordProducer;
    }

    public String whatIHeard() {
        String word = wordProducer.getWord();

        System.out.println(word);

        return word;
    }
}
