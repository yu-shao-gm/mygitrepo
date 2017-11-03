package com.redhat.cp;

import java.io.PrintStream;
import java.util.List;
import org.apache.directory.api.ldap.model.ldif.LdifReader;
import org.apache.directory.api.ldap.model.ldif.LdifEntry;
import org.apache.directory.api.ldap.model.ldif.LdapLdifException;
import org.apache.directory.api.ldap.model.exception.LdapException;
public class App
{
  public App() {}

  public static void main(String[] args)
  {
    try
    {
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
