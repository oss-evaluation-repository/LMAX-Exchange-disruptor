/*
 * Copyright 2011 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lmax.disruptor;

/**
 * Abstraction for claiming a sequence in a data structure while tracking dependent {@link EventProcessor}s
 *
 * @param <T> implementation stored in the {@link RingBuffer}
 */
public interface SequenceManager<T>
{
    /**
     * Get the event for a given sequence from the underlying data structure.
     *
     * @param sequence of the event to get.
     * @return the event for the sequence.
     */
    T get(long sequence);

    /**
     * Get the value of the cursor indicating the published sequence.
     *
     * @return value of the cursor for events that have been published.
     */
    long getCursor();

    /**
     * Claim the next event in sequence for publishing to the {@link RingBuffer}
     *
     * @return the claimed sequence
     */
    long nextSequence();

    /**
     * Claim the next batch sequence numbers for publishing to the {@link RingBuffer}
     *
     * @param sequenceBatch to be updated for the batch range.
     * @return the updated sequenceBatch.
     */
    SequenceBatch nextSequenceBatch(SequenceBatch sequenceBatch);

    /**
     * Publish an event back to the {@link RingBuffer} and make it visible to {@link EventProcessor}s
     * @param sequence to be published from the {@link RingBuffer}
     */
    void publish(long sequence);

    /**
     * Publish the batch of events in sequence to the {@link RingBuffer}.
     *
     * @param sequenceBatch to be published.
     */
    void publish(SequenceBatch sequenceBatch);
}