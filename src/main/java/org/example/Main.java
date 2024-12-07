package org.example;

import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.resources.transcripts.types.*;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        final String fileUrl;
        final String outputTextFile;
        final String jsonOutputFile;
        final String baseUrl = "https://github.com/Froboz67/AudioFiles/raw/refs/heads/main/";



        System.out.println("");
        LocalDate today = LocalDate.now();
        Month month = today.getMonth();
        int day = today.getDayOfMonth();
        int year = today.getYear();
        System.out.println("Today is " + month + " " + day + ", " + year);
        System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println("please paste filename including the extension you wish to convert to text:");
        System.out.print(baseUrl);
        fileUrl = input.nextLine();
        System.out.print("please enter the name for your text file ");
        outputTextFile = input.nextLine();
        System.out.print("please enter the name for the json timestamp file ");
        jsonOutputFile = input.nextLine();

        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("ASSEMBLYAI_API_KEY");

        // accessing the Assembly AI
        AssemblyAI client = AssemblyAI.builder()
                .apiKey(apiKey)
                .build();

        // audio sample
        String audioUrl = baseUrl + fileUrl;

        Transcript transcript = client.transcripts().transcribe(audioUrl);

        if (transcript.getStatus().equals(TranscriptStatus.ERROR)) {
            System.err.println(transcript.getError().get());
            System.exit(1);
        }
        System.out.println(transcript.getText().get());
        System.out.println(transcript.getWords());
        System.out.println(transcript.getSummary());

        /*
        Code below creates and saves the audio transcription as a text file
         */

        String audioTranscription = transcript.getText().get();

        try (BufferedWriter convertedAudio = new BufferedWriter(new FileWriter(outputTextFile + ".txt"))) {
            convertedAudio.write(audioTranscription);
            System.out.println("text file created: " + outputTextFile + ".txt");
        } catch (IOException e) {
            System.out.println("Error creating text file" + e.getMessage());
        }

        /*
        Code below creates and saves the audio transcription with timestamps for each word as a json file
         */

        String jsonScript = String.valueOf(transcript.getWords());

        try (BufferedWriter convertedAudio = new BufferedWriter(new FileWriter(jsonOutputFile + ".json"))) {
            convertedAudio.write(jsonScript);
            System.out.println("json created (this file contains the 'Optional' Java wrapper): " + outputTextFile + ".json");
        } catch (IOException e) {
            System.out.println("Error creating json file" + e.getMessage());
        }
    }
}