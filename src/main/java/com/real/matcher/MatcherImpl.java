package com.real.matcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ch.qos.logback.core.joran.action.ActionConst.NULL;

public class MatcherImpl implements Matcher {

  private static final Logger LOGGER = LoggerFactory.getLogger(MatcherImpl.class);

  Set<Movie> movieSet = new HashSet<>();
  Set<ActorsAndDirector> actorAndDirectorSet = new HashSet<>();

  Set<Xbox> xboxSet = new HashSet<>();

  public MatcherImpl(CsvStream movieDb, CsvStream actorAndDirectorDb) {
    LOGGER.info("importing database");

      movieDb.getDataRows().forEach(row -> {
          String[] items = row.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); // Split using comma but ignore commas within double quotes
          // Extract individual items
          int movieId = items[0].trim().isEmpty() ? null : Integer.parseInt(items[0].trim());
          String movieTitle = items[1].trim().isEmpty() ? null : items[1].replaceAll("^\"|\"$", "").trim(); // Remove double quotes from the title

          System.out.println("The dataaa: "+items[2]);
          int releaseYear = 0;
          if(!items[2].equals("NULL")){
              releaseYear = Integer.parseInt(items[2].trim());
          }
//          int releaseYear = items[2] == null ? null : Integer.parseInt(items[2].trim());
          // Process each row here
          movieSet.add(new Movie(movieId, movieTitle, releaseYear));
      });



    actorAndDirectorDb.getDataRows().forEach(row -> {
      String[] items = row.split(",");
      // Extract individual items
      Long id = Long.valueOf(items[0].trim());
      String name = items[1].trim();
      String role = items[2].trim();
      // Process each row here
      actorAndDirectorSet.add(new ActorsAndDirector(id,name,role));
    });

    // TODO implement me


    LOGGER.info("database imported");
  }

  @Override
  public List<IdMapping> match(DatabaseType databaseType, CsvStream externalDb) {
    // TODO implement me


      List<IdMapping> idMappingList = new ArrayList<>();

      if(databaseType.equals(DatabaseType.XBOX)){
          externalDb.getDataRows().forEach(row -> {
              String[] items = row.split(",");
              // Extract individual items

              Date originalReleaseDate;
              String mediaId = items[0].trim();
              String title = items[1].trim();
              try {
                  originalReleaseDate = this.stringToDate(items[2].trim());
              } catch (ParseException e) {
                  throw new RuntimeException(e);
              }
              String mediaType = items[3].trim();
              String actors = items[4].trim();
              String director = items[5].trim();
              String xboxLiveURL = items[6].trim();

              // Process each row here
              xboxSet.add(new Xbox(mediaId,title,originalReleaseDate,mediaType,actors,director,xboxLiveURL));
          });

      }

      //actual matching logic

      for (Xbox xBox:xboxSet) {

          for (Movie movie:movieSet) {

              if(xBox.getTitle().equalsIgnoreCase(movie.getTitle())){
                  idMappingList.add(new IdMapping(movie.getId(),xBox.getMediaId()));
              }

          }


      }




    return idMappingList;
  }

    private Date stringToDate(String dateString) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.parse(dateString);

    }
}
