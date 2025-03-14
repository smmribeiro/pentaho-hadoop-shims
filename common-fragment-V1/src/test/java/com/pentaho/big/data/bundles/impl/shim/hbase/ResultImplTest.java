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


package com.pentaho.big.data.bundles.impl.shim.hbase;

import org.apache.hadoop.hbase.client.Result;
import org.junit.Before;
import org.junit.Test;
import org.pentaho.hadoop.shim.api.internal.hbase.HBaseBytesUtilShim;

import java.nio.charset.Charset;
import java.util.NavigableMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by bryan on 2/2/16.
 */
public class ResultImplTest {
  public static final Charset UTF_8 = Charset.forName( "UTF-8" );
  private Result delegate;
  private HBaseBytesUtilShim hBaseBytesUtilShim;
  private ResultImpl result;

  @Before
  public void setup() {
    delegate = mock( Result.class );
    hBaseBytesUtilShim = mock( HBaseBytesUtilShim.class );
    result = new ResultImpl( delegate, hBaseBytesUtilShim );
  }

  @Test
  public void testGetRow() {
    byte[] bytes = "row".getBytes( UTF_8 );
    when( delegate.getRow() ).thenReturn( bytes );
    assertArrayEquals( bytes, result.getRow() );
  }

  @Test
  public void testGetMap() {
    NavigableMap navigableMap = mock( NavigableMap.class );
    when( delegate.getMap() ).thenReturn( navigableMap );
    assertEquals( navigableMap, result.getMap() );
  }

  @Test
  public void testGetFamilyMap() {
    NavigableMap navigableMap = mock( NavigableMap.class );
    String familyName = "familyName";
    byte[] bytes = familyName.getBytes( UTF_8 );

    when( hBaseBytesUtilShim.toBytes( familyName ) ).thenReturn( bytes );
    when( delegate.getFamilyMap( bytes ) ).thenReturn( navigableMap );

    assertEquals( navigableMap, result.getFamilyMap( familyName ) );
  }

  @Test
  public void testGetValueBinary() {
    String colFamilyName = "colFamilyName";
    String colName = "colName";
    String value = "value";
    byte[] colFamilyNameBytes = colFamilyName.getBytes( UTF_8 );
    byte[] colNameBytes = colName.getBytes( UTF_8 );
    byte[] valueBytes = value.getBytes( UTF_8 );

    when( hBaseBytesUtilShim.toBytes( colFamilyName ) ).thenReturn( colFamilyNameBytes );
    when( hBaseBytesUtilShim.toBytesBinary( colName ) ).thenReturn( colNameBytes );
    when( delegate.getValue( colFamilyNameBytes, colNameBytes ) ).thenReturn( valueBytes );

    assertArrayEquals( valueBytes, result.getValue( colFamilyName, colName, true ) );
  }

  @Test
  public void testGetValueNotBinary() {
    String colFamilyName = "colFamilyName";
    String colName = "colName";
    String value = "value";
    byte[] colFamilyNameBytes = colFamilyName.getBytes( UTF_8 );
    byte[] colNameBytes = colName.getBytes( UTF_8 );
    byte[] valueBytes = value.getBytes( UTF_8 );

    when( hBaseBytesUtilShim.toBytes( colFamilyName ) ).thenReturn( colFamilyNameBytes );
    when( hBaseBytesUtilShim.toBytes( colName ) ).thenReturn( colNameBytes );
    when( delegate.getValue( colFamilyNameBytes, colNameBytes ) ).thenReturn( valueBytes );

    assertArrayEquals( valueBytes, result.getValue( colFamilyName, colName, false ) );
  }

  @Test
  public void testIsEmpty() {
    when( delegate.isEmpty() ).thenReturn( true ).thenReturn( false );
    assertTrue( result.isEmpty() );
    assertFalse( result.isEmpty() );
  }
}
