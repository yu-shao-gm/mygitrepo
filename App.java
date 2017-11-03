package com.redhat.cp;

import java.io.PrintStream;
import java.util.List;
import org.apache.directory.api.ldap.model.ldif.LdifReader;
import org.apache.directory.api.ldap.model.ldif.LdifEntry;
import org.apache.directory.api.ldap.model.ldif.LdapLdifException;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.api.ldap.model.cursor.Cursor;
import org.apache.directory.api.ldap.model.cursor.EntryCursor;
import org.apache.directory.api.ldap.model.entry.Entry;

public class App
{
  public App() {}
  
  public static void main(String[] args)
  {
      
    try
    {
      System.out.println("Connecting server ...");
      LdapConnection connection = new LdapNetworkConnection( "com", 389 );

    EntryCursor cursor = connection.search( "ou=u", "(objectclass=*)", SearchScope.ONELEVEL );

    for ( Entry entry : cursor )
    {
        System.out.println("=============================");
        //assertNotNull( entry );
        //System.out.println( entry );
          System.out.println(entry.get("cn"));
          System.out.println(entry.get("memberOf"));
          System.out.println(entry.get("rn"));
    }

    cursor.close();

      System.out.println("Closing connection ...");
      connection.close();



    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
    }
    try
    {
      System.out.println("Openning ldif file locally ...");

      LdifReader reader = new LdifReader();

      List<LdifEntry> entries = reader.parseLdifFile("allldap.ldif");
      //String dn = "cn=ravi ravi,mail=xyz@yahoo.com";
      

      for (LdifEntry entry : entries) {
        String name = entry.getDn().getName();
        System.out.println("dn="+name+"EOR");
        //if (name.equals(dn)) {
          System.out.println(entry.get("cn"));
          System.out.println(entry.get("memberOf"));
       
        //  System.out.println(entry.get("mail"));
        //  System.out.println(entry.get("mozillaNickname"));
        //}
      }
    } catch (LdapLdifException e) {
      System.out.println("Error: " + e.getMessage());
    }
    System.out.println("Hello World!");
  }
}
