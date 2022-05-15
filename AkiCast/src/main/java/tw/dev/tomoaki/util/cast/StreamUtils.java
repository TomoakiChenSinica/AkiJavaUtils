/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.util.cast;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

/**
 *
 * @author Tomoaki Chen
 */
public class StreamUtils {

    /**
     * https://www.baeldung.com/java-null-safe-streams-from-collections
     */
    public static <T> Stream<T> collectionToStream(Collection<T> collection) {
        return Optional.ofNullable(collection) //creates an Optional object from the passed-in collection. An empty Optional object is created if the collection is null.
                .map(Collection::stream) // extracts the value contained in the Optional object as an argument to the map method (Collection.stream())
                .orElseGet(Stream::empty); //returns the fallback value in the event that the Optional object is empty, i.e the passed-in collection is null.
    }
}
