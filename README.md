# Audio to Text 

A Java CLI that uses the [AssemblyAI Service](https://www.assemblyai.com/docs/getting-started/transcribe-an-audio-file) to convert audio to text. Inspired by [YouTuber Coding with John](https://www.youtube.com/@CodingWithJohn), whose tutorial sparked the initial creation of this app.

This project introduced me to the AssemblyAI service and deepened my knowledge of the [FFmpeg](https://ffmpeg.org/) multimedia framework, which I use to extract audio from video files via the command line. I'm currently working on integrating FFmpeg directly into the application for a more streamlined experience.

## New Features

I've added functionality to generate a JSON file containing timestamp data from the transcribed audio. This will serve as a foundation for integrating closed captions into current and future videos, ensuring better accessibility for viewers.

## Practical Application

Initially, this app was a learning exercise with no clear practical use beyond personal interest. However, as I started adding videos to my website, I recognized a significant accessibility need for closed captions to support hearing-impaired users. This tool is part of a larger effort to address that need, and I'm now focusing on incorporating closed captions into my videos through future Java developments.

