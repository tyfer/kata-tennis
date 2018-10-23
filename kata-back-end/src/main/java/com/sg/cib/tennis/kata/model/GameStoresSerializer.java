package com.sg.cib.tennis.kata.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * JsonSerializer de la enum {@link GameScores}.
 * La réécriture de la methode serialize permet d'envoyer au flux JSON la vaveur d'affichage
 * à la place du nom.
 */
public class GameStoresSerializer extends JsonSerializer<GameScores> {

    @Override
    public void serialize(GameScores gameScore, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeString(gameScore.getValue());
    }
}
