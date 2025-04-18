/*! ******************************************************************************
 *
 * Pentaho
 *
 * Copyright (C) 2024 by Hitachi Vantara, LLC : http://www.pentaho.com
 *
 * Use of this software is governed by the Business Source License included
 * in the LICENSE.TXT file.
 *
 * Change Date: 2029-07-20
 ******************************************************************************/


package org.pentaho.hadoop.shim.api.mapreduce;

import java.net.URL;

/**
 * Builder interface for configuring MapReduce jobs
 */
public interface MapReduceJobBuilder {
  String STRING_COMBINE_SINGLE_THREADED = "transformation-combine-single-threaded";
  String STRING_REDUCE_SINGLE_THREADED = "transformation-reduce-single-threaded";

  /**
   * Sets the url of the jar to run
   *
   * @param resolvedJarUrl the url of the jar to run
   */
  void setResolvedJarUrl( URL resolvedJarUrl );

  /**
   * Sets the jar to run
   *
   * @param jarUrl the jar to run
   */
  void setJarUrl( String jarUrl );

  /**
   * Sets the job name
   *
   * @param hadoopJobName the job name
   */
  void setHadoopJobName( String hadoopJobName );

  /**
   * Sets the output key class
   *
   * @param outputKeyClass the output key class
   */
  void setOutputKeyClass( String outputKeyClass );

  /**
   * Sets the map output key class
   *
   * @param mapOutputKeyClass the map output key class
   */
  void setMapOutputKeyClass( String mapOutputKeyClass );

  /**
   * Sets the map output value class
   *
   * @param mapOutputValueClass the map output value class
   */
  void setMapOutputValueClass( String mapOutputValueClass );

  /**
   * Sets the map runner class
   *
   * @param mapRunnerClass the map runner class
   */
  void setMapRunnerClass( String mapRunnerClass );

  /**
   * Sets the output value class
   *
   * @param outputValueClass the output value class
   */
  void setOutputValueClass( String outputValueClass );

  /**
   * Sets the mapper class
   *
   * @param mapperClass the mapper class
   */
  void setMapperClass( String mapperClass );

  /**
   * Sets the combiner class
   *
   * @param combinerClass the combiner class
   */
  void setCombinerClass( String combinerClass );

  /**
   * Sets the reducer class
   *
   * @param reducerClass the reducer class
   */
  void setReducerClass( String reducerClass );

  /**
   * Sets the input format class
   *
   * @param inputFormatClass the input format class
   */
  void setInputFormatClass( String inputFormatClass );

  /**
   * Sets the output format class
   *
   * @param outputFormatClass the output format class
   */
  void setOutputFormatClass( String outputFormatClass );

  /**
   * Sets the input paths
   *
   * @param inputPaths
   */
  void setInputPaths( String[] inputPaths );

  /**
   * Sets the number of map tasks
   *
   * @param numMapTasks the number of map tasks
   */
  void setNumMapTasks( int numMapTasks );

  /**
   * Sets the number of reduce tasks
   *
   * @param numReduceTasks the number of reduce tasks
   */
  void setNumReduceTasks( int numReduceTasks );

  /**
   * Sets the output path
   *
   * @param outputPath the output path
   */
  void setOutputPath( String outputPath );

  /**
   * Puts arbitrary variables in the job configuration
   *
   * @param key   the key
   * @param value the value
   */
  void set( String key, String value );

  /**
   * Submits the job to the cluster
   *
   * @return a MapReduceJobAdvanced for monitoring job progress
   * @throws Exception
   */
  MapReduceJobAdvanced submit() throws Exception;
}
