/*
 * $Id: convertPath.js 175 2010-01-28 09:26:52Z kchristiansen $
 * $Revision: 175 $
 * $Date: 2010-01-28 10:26:52 +0100 (Thu, 28 Jan 2010) $
 * $Author: kchristiansen $
 *
 * The DOMS project.
 * Copyright (C) 2007-2010  The State and University Library
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

importClass(java.io.File);
importPackage(Packages.org.apache.tools.ant);
importPackage(Packages.org.apache.tools.ant.types);

importPackage(Packages.org.apache.tools.ant.taskdefs);


// Obtain a reference to a fileset in the enclosing project
dirSet = project.getReference("module.dependencies");

//This requires ant 1.7+ //TODO find another way that is more backwards
includes = dirSet.mergeIncludes(project);

//This requires ant 1.7+
excludes = dirSet.mergeExcludes(project);

var fileset = project.createDataType("fileset");

fileset.setDir(dirSet.getDir());

if (includes != null){
    for (var i=0;i<includes.length;i++){
        fileset.setIncludes(includes[i]+"/dist/**/*.jar");
    }
}

if (excludes != null){
    for (var i=0;i<excludes.length;i++){
        fileset.setExcludes(excludes[i]);
    }
}

project.addReference("module.dependencies.jars",fileset);
