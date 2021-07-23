package com.shetty.moviesdashboard.data_processing;

import javax.sql.DataSource;

import com.shetty.moviesdashboard.data.Movies;
import com.shetty.moviesdashboard.data.MoviesInput;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  @Autowired
  public JobBuilderFactory jobBuilderFactory;

  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  public String[] FIELD_NAMES = {"movieid","title","mpaa_rating","budget","gross","release_date","genre","runtime","rating","rating_count","summary"};

  @Bean
public FlatFileItemReader<MoviesInput> reader() {
  return new FlatFileItemReaderBuilder<MoviesInput>()
    .name("movieItemReader")
    .resource(new ClassPathResource("movies.csv"))
    .delimited()
    .names(FIELD_NAMES)
    .fieldSetMapper(new BeanWrapperFieldSetMapper<MoviesInput>() {{
      setTargetType(MoviesInput.class);
    }})
    .build();
}

@Bean
public MoviesProcessor processor() {
  return new MoviesProcessor();
}

@Bean
public JdbcBatchItemWriter<Movies> writer(DataSource dataSource) {
  return new JdbcBatchItemWriterBuilder<Movies>()
    .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
    .sql("INSERT INTO movies (movieid,title,mpaa_rating,budget,gross,release_date,genre,runtime,rating,rating_count,summary) VALUES (:movieid,:title,:mpaa_rating,:budget,:gross,:release_date,:genre,:runtime,:rating,:rating_count,:summary)")
    .dataSource(dataSource)
    .build();
}

@Bean
public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
  return jobBuilderFactory.get("importUserJob")
    .incrementer(new RunIdIncrementer())
    .listener(listener)
    .flow(step1)
    .end()
    .build();
}

@Bean
public Step step1(JdbcBatchItemWriter<Movies> writer) {
  return stepBuilderFactory.get("step1")
    .<MoviesInput, Movies> chunk(10)
    .reader(reader())
    .processor(processor())
    .writer(writer)
    .build();
}

}