// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package film.call_api_1_0;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: call_api Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class call_api implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "1.0";
	private final String jobName = "call_api";
	private final String projectName = "FILM";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					call_api.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(call_api.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileDelete_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileDelete_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tREST_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tREST_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tExtractJSONFields_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tREST_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFlowToIterate_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tREST_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tREST_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tREST_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tExtractJSONFields_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tREST_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tREST_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileDelete_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tREST_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileDelete_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileDelete_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [tFileDelete_1 begin ] start
				 */

				ok_Hash.put("tFileDelete_1", false);
				start_Hash.put("tFileDelete_1", System.currentTimeMillis());

				currentComponent = "tFileDelete_1";

				int tos_count_tFileDelete_1 = 0;

				/**
				 * [tFileDelete_1 begin ] stop
				 */

				/**
				 * [tFileDelete_1 main ] start
				 */

				currentComponent = "tFileDelete_1";

				class DeleteFoldertFileDelete_1 {
					/**
					 * delete all the sub-files in 'file'
					 * 
					 * @param file
					 */
					public boolean delete(java.io.File file) {
						java.io.File[] files = file.listFiles();
						for (int i = 0; i < files.length; i++) {
							if (files[i].isFile()) {
								files[i].delete();
							} else if (files[i].isDirectory()) {
								if (!files[i].delete()) {
									delete(files[i]);
								}
							}
						}
						deleteDirectory(file);
						return file.delete();
					}

					/**
					 * delete all the sub-folders in 'file'
					 * 
					 * @param file
					 */
					private void deleteDirectory(java.io.File file) {
						java.io.File[] filed = file.listFiles();
						for (int i = 0; i < filed.length; i++) {
							if (filed[i].isDirectory()) {
								deleteDirectory(filed[i]);
							}
							filed[i].delete();
						}
					}

				}
				java.io.File file_tFileDelete_1 = new java.io.File(
						"D:/Applications/TOS_DI-8.0.1/studio/workspace/FILM/_output/film_affiche.csv");
				if (file_tFileDelete_1.exists() && file_tFileDelete_1.isFile()) {
					if (file_tFileDelete_1.delete()) {
						globalMap.put("tFileDelete_1_CURRENT_STATUS", "File deleted.");
					} else {
						globalMap.put("tFileDelete_1_CURRENT_STATUS", "No file deleted.");
						throw new RuntimeException(
								"File " + file_tFileDelete_1.getAbsolutePath() + " can not be deleted.");
					}
				} else {
					globalMap.put("tFileDelete_1_CURRENT_STATUS", "File does not exist or is invalid.");
					throw new RuntimeException("File " + file_tFileDelete_1.getAbsolutePath()
							+ " does not exist or is invalid or is not a file.");
				}
				globalMap.put("tFileDelete_1_DELETE_PATH",
						"D:/Applications/TOS_DI-8.0.1/studio/workspace/FILM/_output/film_affiche.csv");

				tos_count_tFileDelete_1++;

				/**
				 * [tFileDelete_1 main ] stop
				 */

				/**
				 * [tFileDelete_1 process_data_begin ] start
				 */

				currentComponent = "tFileDelete_1";

				/**
				 * [tFileDelete_1 process_data_begin ] stop
				 */

				/**
				 * [tFileDelete_1 process_data_end ] start
				 */

				currentComponent = "tFileDelete_1";

				/**
				 * [tFileDelete_1 process_data_end ] stop
				 */

				/**
				 * [tFileDelete_1 end ] start
				 */

				currentComponent = "tFileDelete_1";

				ok_Hash.put("tFileDelete_1", true);
				end_Hash.put("tFileDelete_1", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk1", 0, "ok");
				}
				tREST_1Process(globalMap);

				/**
				 * [tFileDelete_1 end ] stop
				 */
			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileDelete_1 finally ] start
				 */

				currentComponent = "tFileDelete_1";

				/**
				 * [tFileDelete_1 finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileDelete_1_SUBPROCESS_STATE", 1);
	}

	public static class film2Struct implements routines.system.IPersistableRow<film2Struct> {
		final static byte[] commonByteArrayLock_FILM_call_api = new byte[0];
		static byte[] commonByteArray_FILM_call_api = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public String budget;

		public String getBudget() {
			return this.budget;
		}

		public String genres;

		public String getGenres() {
			return this.genres;
		}

		public String original_language;

		public String getOriginal_language() {
			return this.original_language;
		}

		public String original_title;

		public String getOriginal_title() {
			return this.original_title;
		}

		public String title;

		public String getTitle() {
			return this.title;
		}

		public String overview;

		public String getOverview() {
			return this.overview;
		}

		public String poster_path;

		public String getPoster_path() {
			return this.poster_path;
		}

		public String production_companies;

		public String getProduction_companies() {
			return this.production_companies;
		}

		public String release_date;

		public String getRelease_date() {
			return this.release_date;
		}

		public String revenue;

		public String getRevenue() {
			return this.revenue;
		}

		public String runtime;

		public String getRuntime() {
			return this.runtime;
		}

		public String vote_average;

		public String getVote_average() {
			return this.vote_average;
		}

		public String vote_count;

		public String getVote_count() {
			return this.vote_count;
		}

		public String tagline;

		public String getTagline() {
			return this.tagline;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final film2Struct other = (film2Struct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(film2Struct other) {

			other.id = this.id;
			other.budget = this.budget;
			other.genres = this.genres;
			other.original_language = this.original_language;
			other.original_title = this.original_title;
			other.title = this.title;
			other.overview = this.overview;
			other.poster_path = this.poster_path;
			other.production_companies = this.production_companies;
			other.release_date = this.release_date;
			other.revenue = this.revenue;
			other.runtime = this.runtime;
			other.vote_average = this.vote_average;
			other.vote_count = this.vote_count;
			other.tagline = this.tagline;

		}

		public void copyKeysDataTo(film2Struct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FILM_call_api.length) {
					if (length < 1024 && commonByteArray_FILM_call_api.length == 0) {
						commonByteArray_FILM_call_api = new byte[1024];
					} else {
						commonByteArray_FILM_call_api = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_FILM_call_api, 0, length);
				strReturn = new String(commonByteArray_FILM_call_api, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FILM_call_api.length) {
					if (length < 1024 && commonByteArray_FILM_call_api.length == 0) {
						commonByteArray_FILM_call_api = new byte[1024];
					} else {
						commonByteArray_FILM_call_api = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_FILM_call_api, 0, length);
				strReturn = new String(commonByteArray_FILM_call_api, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_FILM_call_api) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.budget = readString(dis);

					this.genres = readString(dis);

					this.original_language = readString(dis);

					this.original_title = readString(dis);

					this.title = readString(dis);

					this.overview = readString(dis);

					this.poster_path = readString(dis);

					this.production_companies = readString(dis);

					this.release_date = readString(dis);

					this.revenue = readString(dis);

					this.runtime = readString(dis);

					this.vote_average = readString(dis);

					this.vote_count = readString(dis);

					this.tagline = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_FILM_call_api) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.budget = readString(dis);

					this.genres = readString(dis);

					this.original_language = readString(dis);

					this.original_title = readString(dis);

					this.title = readString(dis);

					this.overview = readString(dis);

					this.poster_path = readString(dis);

					this.production_companies = readString(dis);

					this.release_date = readString(dis);

					this.revenue = readString(dis);

					this.runtime = readString(dis);

					this.vote_average = readString(dis);

					this.vote_count = readString(dis);

					this.tagline = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.budget, dos);

				// String

				writeString(this.genres, dos);

				// String

				writeString(this.original_language, dos);

				// String

				writeString(this.original_title, dos);

				// String

				writeString(this.title, dos);

				// String

				writeString(this.overview, dos);

				// String

				writeString(this.poster_path, dos);

				// String

				writeString(this.production_companies, dos);

				// String

				writeString(this.release_date, dos);

				// String

				writeString(this.revenue, dos);

				// String

				writeString(this.runtime, dos);

				// String

				writeString(this.vote_average, dos);

				// String

				writeString(this.vote_count, dos);

				// String

				writeString(this.tagline, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.budget, dos);

				// String

				writeString(this.genres, dos);

				// String

				writeString(this.original_language, dos);

				// String

				writeString(this.original_title, dos);

				// String

				writeString(this.title, dos);

				// String

				writeString(this.overview, dos);

				// String

				writeString(this.poster_path, dos);

				// String

				writeString(this.production_companies, dos);

				// String

				writeString(this.release_date, dos);

				// String

				writeString(this.revenue, dos);

				// String

				writeString(this.runtime, dos);

				// String

				writeString(this.vote_average, dos);

				// String

				writeString(this.vote_count, dos);

				// String

				writeString(this.tagline, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",budget=" + budget);
			sb.append(",genres=" + genres);
			sb.append(",original_language=" + original_language);
			sb.append(",original_title=" + original_title);
			sb.append(",title=" + title);
			sb.append(",overview=" + overview);
			sb.append(",poster_path=" + poster_path);
			sb.append(",production_companies=" + production_companies);
			sb.append(",release_date=" + release_date);
			sb.append(",revenue=" + revenue);
			sb.append(",runtime=" + runtime);
			sb.append(",vote_average=" + vote_average);
			sb.append(",vote_count=" + vote_count);
			sb.append(",tagline=" + tagline);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(film2Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id, other.id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class film1Struct implements routines.system.IPersistableRow<film1Struct> {
		final static byte[] commonByteArrayLock_FILM_call_api = new byte[0];
		static byte[] commonByteArray_FILM_call_api = new byte[0];

		public String Body;

		public String getBody() {
			return this.Body;
		}

		public Integer ERROR_CODE;

		public Integer getERROR_CODE() {
			return this.ERROR_CODE;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FILM_call_api.length) {
					if (length < 1024 && commonByteArray_FILM_call_api.length == 0) {
						commonByteArray_FILM_call_api = new byte[1024];
					} else {
						commonByteArray_FILM_call_api = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_FILM_call_api, 0, length);
				strReturn = new String(commonByteArray_FILM_call_api, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FILM_call_api.length) {
					if (length < 1024 && commonByteArray_FILM_call_api.length == 0) {
						commonByteArray_FILM_call_api = new byte[1024];
					} else {
						commonByteArray_FILM_call_api = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_FILM_call_api, 0, length);
				strReturn = new String(commonByteArray_FILM_call_api, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_FILM_call_api) {

				try {

					int length = 0;

					this.Body = readString(dis);

					this.ERROR_CODE = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_FILM_call_api) {

				try {

					int length = 0;

					this.Body = readString(dis);

					this.ERROR_CODE = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Body, dos);

				// Integer

				writeInteger(this.ERROR_CODE, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Body, dos);

				// Integer

				writeInteger(this.ERROR_CODE, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Body=" + Body);
			sb.append(",ERROR_CODE=" + String.valueOf(ERROR_CODE));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(film1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class films2Struct implements routines.system.IPersistableRow<films2Struct> {
		final static byte[] commonByteArrayLock_FILM_call_api = new byte[0];
		static byte[] commonByteArray_FILM_call_api = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String id;

		public String getId() {
			return this.id;
		}

		public String title;

		public String getTitle() {
			return this.title;
		}

		public String original_title;

		public String getOriginal_title() {
			return this.original_title;
		}

		public String release_date;

		public String getRelease_date() {
			return this.release_date;
		}

		public String original_language;

		public String getOriginal_language() {
			return this.original_language;
		}

		public String genre_ids;

		public String getGenre_ids() {
			return this.genre_ids;
		}

		public String overview;

		public String getOverview() {
			return this.overview;
		}

		public Boolean adult;

		public Boolean getAdult() {
			return this.adult;
		}

		public String poster_path;

		public String getPoster_path() {
			return this.poster_path;
		}

		public Float vote_average;

		public Float getVote_average() {
			return this.vote_average;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final films2Struct other = (films2Struct) obj;

			if (this.id == null) {
				if (other.id != null)
					return false;

			} else if (!this.id.equals(other.id))

				return false;

			return true;
		}

		public void copyDataTo(films2Struct other) {

			other.id = this.id;
			other.title = this.title;
			other.original_title = this.original_title;
			other.release_date = this.release_date;
			other.original_language = this.original_language;
			other.genre_ids = this.genre_ids;
			other.overview = this.overview;
			other.adult = this.adult;
			other.poster_path = this.poster_path;
			other.vote_average = this.vote_average;

		}

		public void copyKeysDataTo(films2Struct other) {

			other.id = this.id;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FILM_call_api.length) {
					if (length < 1024 && commonByteArray_FILM_call_api.length == 0) {
						commonByteArray_FILM_call_api = new byte[1024];
					} else {
						commonByteArray_FILM_call_api = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_FILM_call_api, 0, length);
				strReturn = new String(commonByteArray_FILM_call_api, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FILM_call_api.length) {
					if (length < 1024 && commonByteArray_FILM_call_api.length == 0) {
						commonByteArray_FILM_call_api = new byte[1024];
					} else {
						commonByteArray_FILM_call_api = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_FILM_call_api, 0, length);
				strReturn = new String(commonByteArray_FILM_call_api, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_FILM_call_api) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.title = readString(dis);

					this.original_title = readString(dis);

					this.release_date = readString(dis);

					this.original_language = readString(dis);

					this.genre_ids = readString(dis);

					this.overview = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.adult = null;
					} else {
						this.adult = dis.readBoolean();
					}

					this.poster_path = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.vote_average = null;
					} else {
						this.vote_average = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_FILM_call_api) {

				try {

					int length = 0;

					this.id = readString(dis);

					this.title = readString(dis);

					this.original_title = readString(dis);

					this.release_date = readString(dis);

					this.original_language = readString(dis);

					this.genre_ids = readString(dis);

					this.overview = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.adult = null;
					} else {
						this.adult = dis.readBoolean();
					}

					this.poster_path = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.vote_average = null;
					} else {
						this.vote_average = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.title, dos);

				// String

				writeString(this.original_title, dos);

				// String

				writeString(this.release_date, dos);

				// String

				writeString(this.original_language, dos);

				// String

				writeString(this.genre_ids, dos);

				// String

				writeString(this.overview, dos);

				// Boolean

				if (this.adult == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.adult);
				}

				// String

				writeString(this.poster_path, dos);

				// Float

				if (this.vote_average == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.vote_average);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.id, dos);

				// String

				writeString(this.title, dos);

				// String

				writeString(this.original_title, dos);

				// String

				writeString(this.release_date, dos);

				// String

				writeString(this.original_language, dos);

				// String

				writeString(this.genre_ids, dos);

				// String

				writeString(this.overview, dos);

				// Boolean

				if (this.adult == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeBoolean(this.adult);
				}

				// String

				writeString(this.poster_path, dos);

				// Float

				if (this.vote_average == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.vote_average);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id=" + id);
			sb.append(",title=" + title);
			sb.append(",original_title=" + original_title);
			sb.append(",release_date=" + release_date);
			sb.append(",original_language=" + original_language);
			sb.append(",genre_ids=" + genre_ids);
			sb.append(",overview=" + overview);
			sb.append(",adult=" + String.valueOf(adult));
			sb.append(",poster_path=" + poster_path);
			sb.append(",vote_average=" + String.valueOf(vote_average));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(films2Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id, other.id);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class films1Struct implements routines.system.IPersistableRow<films1Struct> {
		final static byte[] commonByteArrayLock_FILM_call_api = new byte[0];
		static byte[] commonByteArray_FILM_call_api = new byte[0];

		public String Body;

		public String getBody() {
			return this.Body;
		}

		public Integer ERROR_CODE;

		public Integer getERROR_CODE() {
			return this.ERROR_CODE;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FILM_call_api.length) {
					if (length < 1024 && commonByteArray_FILM_call_api.length == 0) {
						commonByteArray_FILM_call_api = new byte[1024];
					} else {
						commonByteArray_FILM_call_api = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_FILM_call_api, 0, length);
				strReturn = new String(commonByteArray_FILM_call_api, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_FILM_call_api.length) {
					if (length < 1024 && commonByteArray_FILM_call_api.length == 0) {
						commonByteArray_FILM_call_api = new byte[1024];
					} else {
						commonByteArray_FILM_call_api = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_FILM_call_api, 0, length);
				strReturn = new String(commonByteArray_FILM_call_api, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_FILM_call_api) {

				try {

					int length = 0;

					this.Body = readString(dis);

					this.ERROR_CODE = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_FILM_call_api) {

				try {

					int length = 0;

					this.Body = readString(dis);

					this.ERROR_CODE = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Body, dos);

				// Integer

				writeInteger(this.ERROR_CODE, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Body, dos);

				// Integer

				writeInteger(this.ERROR_CODE, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Body=" + Body);
			sb.append(",ERROR_CODE=" + String.valueOf(ERROR_CODE));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(films1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tREST_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tREST_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				films1Struct films1 = new films1Struct();
				films2Struct films2 = new films2Struct();
				film1Struct film1 = new film1Struct();
				film2Struct film2 = new film2Struct();

				/**
				 * [tFlowToIterate_1 begin ] start
				 */

				int NB_ITERATE_tREST_2 = 0; // for statistics

				ok_Hash.put("tFlowToIterate_1", false);
				start_Hash.put("tFlowToIterate_1", System.currentTimeMillis());

				currentComponent = "tFlowToIterate_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "films2");
				}

				int tos_count_tFlowToIterate_1 = 0;

				int nb_line_tFlowToIterate_1 = 0;
				int counter_tFlowToIterate_1 = 0;

				/**
				 * [tFlowToIterate_1 begin ] stop
				 */

				/**
				 * [tExtractJSONFields_1 begin ] start
				 */

				ok_Hash.put("tExtractJSONFields_1", false);
				start_Hash.put("tExtractJSONFields_1", System.currentTimeMillis());

				currentComponent = "tExtractJSONFields_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "films1");
				}

				int tos_count_tExtractJSONFields_1 = 0;

				int nb_line_tExtractJSONFields_1 = 0;
				String jsonStr_tExtractJSONFields_1 = "";

				class JsonPathCache_tExtractJSONFields_1 {
					final java.util.Map<String, com.jayway.jsonpath.JsonPath> jsonPathString2compiledJsonPath = new java.util.HashMap<String, com.jayway.jsonpath.JsonPath>();

					public com.jayway.jsonpath.JsonPath getCompiledJsonPath(String jsonPath) {
						if (jsonPathString2compiledJsonPath.containsKey(jsonPath)) {
							return jsonPathString2compiledJsonPath.get(jsonPath);
						} else {
							com.jayway.jsonpath.JsonPath compiledLoopPath = com.jayway.jsonpath.JsonPath
									.compile(jsonPath);
							jsonPathString2compiledJsonPath.put(jsonPath, compiledLoopPath);
							return compiledLoopPath;
						}
					}
				}

				JsonPathCache_tExtractJSONFields_1 jsonPathCache_tExtractJSONFields_1 = new JsonPathCache_tExtractJSONFields_1();

				/**
				 * [tExtractJSONFields_1 begin ] stop
				 */

				/**
				 * [tREST_1 begin ] start
				 */

				ok_Hash.put("tREST_1", false);
				start_Hash.put("tREST_1", System.currentTimeMillis());

				currentComponent = "tREST_1";

				int tos_count_tREST_1 = 0;

				String endpoint_tREST_1 = "https://api.themoviedb.org/3/movie/now_playing?language=fr-FR&page=1'";

				String trustStoreFile_tREST_1 = System.getProperty("javax.net.ssl.trustStore");
				String trustStoreType_tREST_1 = System.getProperty("javax.net.ssl.trustStoreType");
				String trustStorePWD_tREST_1 = System.getProperty("javax.net.ssl.trustStorePassword");

				String keyStoreFile_tREST_1 = System.getProperty("javax.net.ssl.keyStore");
				String keyStoreType_tREST_1 = System.getProperty("javax.net.ssl.keyStoreType");
				String keyStorePWD_tREST_1 = System.getProperty("javax.net.ssl.keyStorePassword");

				com.sun.jersey.api.client.config.ClientConfig config_tREST_1 = new com.sun.jersey.api.client.config.DefaultClientConfig();
				javax.net.ssl.SSLContext ctx_tREST_1 = javax.net.ssl.SSLContext.getInstance("SSL");

				javax.net.ssl.TrustManager[] tms_tREST_1 = null;
				if (trustStoreFile_tREST_1 != null && trustStoreType_tREST_1 != null) {
					char[] password_tREST_1 = null;
					if (trustStorePWD_tREST_1 != null)
						password_tREST_1 = trustStorePWD_tREST_1.toCharArray();
					java.security.KeyStore trustStore_tREST_1 = java.security.KeyStore
							.getInstance(trustStoreType_tREST_1);
					trustStore_tREST_1.load(new java.io.FileInputStream(trustStoreFile_tREST_1), password_tREST_1);

					javax.net.ssl.TrustManagerFactory tmf_tREST_1 = javax.net.ssl.TrustManagerFactory
							.getInstance(javax.net.ssl.KeyManagerFactory.getDefaultAlgorithm());
					tmf_tREST_1.init(trustStore_tREST_1);
					tms_tREST_1 = tmf_tREST_1.getTrustManagers();
				}

				javax.net.ssl.KeyManager[] kms_tREST_1 = null;
				if (keyStoreFile_tREST_1 != null && keyStoreType_tREST_1 != null) {
					char[] password_tREST_1 = null;
					if (keyStorePWD_tREST_1 != null)
						password_tREST_1 = keyStorePWD_tREST_1.toCharArray();
					java.security.KeyStore keyStore_tREST_1 = java.security.KeyStore.getInstance(keyStoreType_tREST_1);
					keyStore_tREST_1.load(new java.io.FileInputStream(keyStoreFile_tREST_1), password_tREST_1);

					javax.net.ssl.KeyManagerFactory kmf_tREST_1 = javax.net.ssl.KeyManagerFactory
							.getInstance(javax.net.ssl.KeyManagerFactory.getDefaultAlgorithm());
					kmf_tREST_1.init(keyStore_tREST_1, password_tREST_1);
					kms_tREST_1 = kmf_tREST_1.getKeyManagers();
				}

				ctx_tREST_1.init(kms_tREST_1, tms_tREST_1, null);
				config_tREST_1.getProperties().put(
						com.sun.jersey.client.urlconnection.HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
						new com.sun.jersey.client.urlconnection.HTTPSProperties(new javax.net.ssl.HostnameVerifier() {

							public boolean verify(String hostName, javax.net.ssl.SSLSession session) {
								return true;
							}
						}, ctx_tREST_1));

				com.sun.jersey.api.client.Client restClient_tREST_1 = com.sun.jersey.api.client.Client
						.create(config_tREST_1);

				java.util.Map<String, Object> headers_tREST_1 = new java.util.HashMap<String, Object>();

				headers_tREST_1.put("Authorization",
						"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ZDA2OGNiNzdmMDdiYWQ1NjQ4YmVjNzMyZDdjZmU4MiIsInN1YiI6IjY1YThkZTc3MGU1YWJhMDEzODdkZTIwOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.EDcWYM5XLM8Ku9ab4qhMOhwFnNaJqoIykiFCW6KT_6Q");

				Object transfer_encoding_tREST_1 = headers_tREST_1.get("Transfer-Encoding");
				if (transfer_encoding_tREST_1 != null && "chunked".equals(transfer_encoding_tREST_1)) {
					restClient_tREST_1.setChunkedEncodingSize(4096);
				}

				com.sun.jersey.api.client.WebResource restResource_tREST_1;
				if (endpoint_tREST_1 != null && !("").equals(endpoint_tREST_1)) {
					restResource_tREST_1 = restClient_tREST_1.resource(endpoint_tREST_1);
				} else {
					throw new IllegalArgumentException("url can't be empty!");
				}

				com.sun.jersey.api.client.ClientResponse errorResponse_tREST_1 = null;
				String restResponse_tREST_1 = "";
				try {

					com.sun.jersey.api.client.WebResource.Builder builder_tREST_1 = null;
					for (java.util.Map.Entry<String, Object> header_tREST_1 : headers_tREST_1.entrySet()) {
						if (builder_tREST_1 == null) {
							builder_tREST_1 = restResource_tREST_1.header(header_tREST_1.getKey(),
									header_tREST_1.getValue());
						} else {
							builder_tREST_1.header(header_tREST_1.getKey(), header_tREST_1.getValue());
						}
					}

					if (builder_tREST_1 != null) {
						restResponse_tREST_1 = builder_tREST_1.get(String.class);
					} else {
						restResponse_tREST_1 = restResource_tREST_1.get(String.class);
					}

				} catch (com.sun.jersey.api.client.UniformInterfaceException ue) {
					globalMap.put("tREST_1_ERROR_MESSAGE", ue.getMessage());
					errorResponse_tREST_1 = ue.getResponse();
				}

				// for output

				films1 = new films1Struct();
				if (errorResponse_tREST_1 != null) {
					films1.ERROR_CODE = errorResponse_tREST_1.getStatus();
					if (films1.ERROR_CODE != 204) {
						films1.Body = errorResponse_tREST_1.getEntity(String.class);
					}
				} else {
					films1.Body = restResponse_tREST_1;
				}

				/**
				 * [tREST_1 begin ] stop
				 */

				/**
				 * [tREST_1 main ] start
				 */

				currentComponent = "tREST_1";

				tos_count_tREST_1++;

				/**
				 * [tREST_1 main ] stop
				 */

				/**
				 * [tREST_1 process_data_begin ] start
				 */

				currentComponent = "tREST_1";

				/**
				 * [tREST_1 process_data_begin ] stop
				 */

				/**
				 * [tExtractJSONFields_1 main ] start
				 */

				currentComponent = "tExtractJSONFields_1";

				if (execStat) {
					runStat.updateStatOnConnection(iterateId, 1, 1

							, "films1"

					);
				}

				if (films1.Body != null) {// C_01
					jsonStr_tExtractJSONFields_1 = films1.Body.toString();

					films2 = null;

					String loopPath_tExtractJSONFields_1 = "$.results[*]";
					java.util.List<Object> resultset_tExtractJSONFields_1 = new java.util.ArrayList<Object>();

					boolean isStructError_tExtractJSONFields_1 = true;
					com.jayway.jsonpath.ReadContext document_tExtractJSONFields_1 = null;
					try {
						document_tExtractJSONFields_1 = com.jayway.jsonpath.JsonPath
								.parse(jsonStr_tExtractJSONFields_1);
						com.jayway.jsonpath.JsonPath compiledLoopPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
								.getCompiledJsonPath(loopPath_tExtractJSONFields_1);
						Object result_tExtractJSONFields_1 = document_tExtractJSONFields_1
								.read(compiledLoopPath_tExtractJSONFields_1, net.minidev.json.JSONObject.class);
						if (result_tExtractJSONFields_1 instanceof net.minidev.json.JSONArray) {
							resultset_tExtractJSONFields_1 = (net.minidev.json.JSONArray) result_tExtractJSONFields_1;
						} else {
							resultset_tExtractJSONFields_1.add(result_tExtractJSONFields_1);
						}

						isStructError_tExtractJSONFields_1 = false;
					} catch (java.lang.Exception ex_tExtractJSONFields_1) {
						globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE", ex_tExtractJSONFields_1.getMessage());
						System.err.println(ex_tExtractJSONFields_1.getMessage());
					}

					String jsonPath_tExtractJSONFields_1 = null;
					com.jayway.jsonpath.JsonPath compiledJsonPath_tExtractJSONFields_1 = null;

					Object value_tExtractJSONFields_1 = null;

					Object root_tExtractJSONFields_1 = null;
					for (int i_tExtractJSONFields_1 = 0; isStructError_tExtractJSONFields_1
							|| (i_tExtractJSONFields_1 < resultset_tExtractJSONFields_1
									.size()); i_tExtractJSONFields_1++) {
						if (!isStructError_tExtractJSONFields_1) {
							Object row_tExtractJSONFields_1 = resultset_tExtractJSONFields_1
									.get(i_tExtractJSONFields_1);
							films2 = null;
							films2 = new films2Struct();
							nb_line_tExtractJSONFields_1++;
							try {
								jsonPath_tExtractJSONFields_1 = "id";
								compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
										.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

								try {

									value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
											.read(row_tExtractJSONFields_1);

									films2.id = value_tExtractJSONFields_1 == null ?

											null

											: value_tExtractJSONFields_1.toString();
								} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
									globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
											e_tExtractJSONFields_1.getMessage());
									films2.id =

											null

									;
								}
								jsonPath_tExtractJSONFields_1 = "title";
								compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
										.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

								try {

									value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
											.read(row_tExtractJSONFields_1);

									films2.title = value_tExtractJSONFields_1 == null ?

											null

											: value_tExtractJSONFields_1.toString();
								} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
									globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
											e_tExtractJSONFields_1.getMessage());
									films2.title =

											null

									;
								}
								jsonPath_tExtractJSONFields_1 = "original_title";
								compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
										.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

								try {

									value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
											.read(row_tExtractJSONFields_1);

									films2.original_title = value_tExtractJSONFields_1 == null ?

											null

											: value_tExtractJSONFields_1.toString();
								} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
									globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
											e_tExtractJSONFields_1.getMessage());
									films2.original_title =

											null

									;
								}
								jsonPath_tExtractJSONFields_1 = "release_date";
								compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
										.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

								try {

									value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
											.read(row_tExtractJSONFields_1);

									films2.release_date = value_tExtractJSONFields_1 == null ?

											null

											: value_tExtractJSONFields_1.toString();
								} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
									globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
											e_tExtractJSONFields_1.getMessage());
									films2.release_date =

											null

									;
								}
								jsonPath_tExtractJSONFields_1 = "original_language";
								compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
										.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

								try {

									value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
											.read(row_tExtractJSONFields_1);

									films2.original_language = value_tExtractJSONFields_1 == null ?

											null

											: value_tExtractJSONFields_1.toString();
								} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
									globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
											e_tExtractJSONFields_1.getMessage());
									films2.original_language =

											null

									;
								}
								jsonPath_tExtractJSONFields_1 = "genre_ids";
								compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
										.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

								try {

									value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
											.read(row_tExtractJSONFields_1);

									films2.genre_ids = value_tExtractJSONFields_1 == null ?

											null

											: value_tExtractJSONFields_1.toString();
								} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
									globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
											e_tExtractJSONFields_1.getMessage());
									films2.genre_ids =

											null

									;
								}
								jsonPath_tExtractJSONFields_1 = "overview";
								compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
										.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

								try {

									value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
											.read(row_tExtractJSONFields_1);

									films2.overview = value_tExtractJSONFields_1 == null ?

											null

											: value_tExtractJSONFields_1.toString();
								} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
									globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
											e_tExtractJSONFields_1.getMessage());
									films2.overview =

											null

									;
								}
								jsonPath_tExtractJSONFields_1 = "adult";
								compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
										.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

								try {

									value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
											.read(row_tExtractJSONFields_1);

									if (value_tExtractJSONFields_1 != null
											&& !value_tExtractJSONFields_1.toString().isEmpty()) {
										films2.adult = ParserUtils
												.parseTo_Boolean(value_tExtractJSONFields_1.toString());
									} else {
										films2.adult =

												null

										;
									}
								} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
									globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
											e_tExtractJSONFields_1.getMessage());
									films2.adult =

											null

									;
								}
								jsonPath_tExtractJSONFields_1 = "poster_path";
								compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
										.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

								try {

									value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
											.read(row_tExtractJSONFields_1);

									films2.poster_path = value_tExtractJSONFields_1 == null ?

											null

											: value_tExtractJSONFields_1.toString();
								} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
									globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
											e_tExtractJSONFields_1.getMessage());
									films2.poster_path =

											null

									;
								}
								jsonPath_tExtractJSONFields_1 = "vote_average";
								compiledJsonPath_tExtractJSONFields_1 = jsonPathCache_tExtractJSONFields_1
										.getCompiledJsonPath(jsonPath_tExtractJSONFields_1);

								try {

									value_tExtractJSONFields_1 = compiledJsonPath_tExtractJSONFields_1
											.read(row_tExtractJSONFields_1);

									if (value_tExtractJSONFields_1 != null
											&& !value_tExtractJSONFields_1.toString().isEmpty()) {
										films2.vote_average = ParserUtils
												.parseTo_Float(value_tExtractJSONFields_1.toString());
									} else {
										films2.vote_average =

												null

										;
									}
								} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_1) {
									globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
											e_tExtractJSONFields_1.getMessage());
									films2.vote_average =

											null

									;
								}
							} catch (java.lang.Exception ex_tExtractJSONFields_1) {
								globalMap.put("tExtractJSONFields_1_ERROR_MESSAGE",
										ex_tExtractJSONFields_1.getMessage());
								System.err.println(ex_tExtractJSONFields_1.getMessage());
								films2 = null;
							}

						}

						isStructError_tExtractJSONFields_1 = false;

//}

						tos_count_tExtractJSONFields_1++;

						/**
						 * [tExtractJSONFields_1 main ] stop
						 */

						/**
						 * [tExtractJSONFields_1 process_data_begin ] start
						 */

						currentComponent = "tExtractJSONFields_1";

						/**
						 * [tExtractJSONFields_1 process_data_begin ] stop
						 */
// Start of branch "films2"
						if (films2 != null) {

							/**
							 * [tFlowToIterate_1 main ] start
							 */

							currentComponent = "tFlowToIterate_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "films2"

								);
							}

							globalMap.put("films2.id", films2.id);

							globalMap.put("films2.title", films2.title);

							globalMap.put("films2.original_title", films2.original_title);

							globalMap.put("films2.release_date", films2.release_date);

							globalMap.put("films2.original_language", films2.original_language);

							globalMap.put("films2.genre_ids", films2.genre_ids);

							globalMap.put("films2.overview", films2.overview);

							globalMap.put("films2.adult", films2.adult);

							globalMap.put("films2.poster_path", films2.poster_path);

							globalMap.put("films2.vote_average", films2.vote_average);

							nb_line_tFlowToIterate_1++;
							counter_tFlowToIterate_1++;
							globalMap.put("tFlowToIterate_1_CURRENT_ITERATION", counter_tFlowToIterate_1);

							tos_count_tFlowToIterate_1++;

							/**
							 * [tFlowToIterate_1 main ] stop
							 */

							/**
							 * [tFlowToIterate_1 process_data_begin ] start
							 */

							currentComponent = "tFlowToIterate_1";

							/**
							 * [tFlowToIterate_1 process_data_begin ] stop
							 */
							NB_ITERATE_tREST_2++;

							if (execStat) {
								runStat.updateStatOnConnection("film2", 3, 0);
							}

							if (execStat) {
								runStat.updateStatOnConnection("film1", 3, 0);
							}

							if (execStat) {
								runStat.updateStatOnConnection("iterate1", 1, "exec" + NB_ITERATE_tREST_2);
								// Thread.sleep(1000);
							}

							/**
							 * [tFileOutputDelimited_1 begin ] start
							 */

							ok_Hash.put("tFileOutputDelimited_1", false);
							start_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

							currentComponent = "tFileOutputDelimited_1";

							if (execStat) {
								runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "film2");
							}

							int tos_count_tFileOutputDelimited_1 = 0;

							String fileName_tFileOutputDelimited_1 = "";
							fileName_tFileOutputDelimited_1 = (new java.io.File(
									"D:/Applications/TOS_DI-8.0.1/studio/workspace/FILM/_output/film_affiche.csv"))
											.getAbsolutePath().replace("\\", "/");
							String fullName_tFileOutputDelimited_1 = null;
							String extension_tFileOutputDelimited_1 = null;
							String directory_tFileOutputDelimited_1 = null;
							if ((fileName_tFileOutputDelimited_1.indexOf("/") != -1)) {
								if (fileName_tFileOutputDelimited_1.lastIndexOf(".") < fileName_tFileOutputDelimited_1
										.lastIndexOf("/")) {
									fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
									extension_tFileOutputDelimited_1 = "";
								} else {
									fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
											fileName_tFileOutputDelimited_1.lastIndexOf("."));
									extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
											.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
								}
								directory_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
										fileName_tFileOutputDelimited_1.lastIndexOf("/"));
							} else {
								if (fileName_tFileOutputDelimited_1.lastIndexOf(".") != -1) {
									fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
											fileName_tFileOutputDelimited_1.lastIndexOf("."));
									extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
											.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
								} else {
									fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
									extension_tFileOutputDelimited_1 = "";
								}
								directory_tFileOutputDelimited_1 = "";
							}
							boolean isFileGenerated_tFileOutputDelimited_1 = true;
							java.io.File filetFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
							globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);
							if (filetFileOutputDelimited_1.exists()) {
								isFileGenerated_tFileOutputDelimited_1 = false;
							}
							int nb_line_tFileOutputDelimited_1 = 0;
							int splitedFileNo_tFileOutputDelimited_1 = 0;
							int currentRow_tFileOutputDelimited_1 = 0;

							final String OUT_DELIM_tFileOutputDelimited_1 = /**
																			 * Start field
																			 * tFileOutputDelimited_1:FIELDSEPARATOR
																			 */
									";"/** End field tFileOutputDelimited_1:FIELDSEPARATOR */
							;

							final String OUT_DELIM_ROWSEP_tFileOutputDelimited_1 = /**
																					 * Start field
																					 * tFileOutputDelimited_1:ROWSEPARATOR
																					 */
									"\n"/** End field tFileOutputDelimited_1:ROWSEPARATOR */
							;

							// create directory only if not exists
							if (directory_tFileOutputDelimited_1 != null
									&& directory_tFileOutputDelimited_1.trim().length() != 0) {
								java.io.File dir_tFileOutputDelimited_1 = new java.io.File(
										directory_tFileOutputDelimited_1);
								if (!dir_tFileOutputDelimited_1.exists()) {
									dir_tFileOutputDelimited_1.mkdirs();
								}
							}

							// routines.system.Row
							java.io.Writer outtFileOutputDelimited_1 = null;

							outtFileOutputDelimited_1 = new routines.system.BufferedOutput(
									new java.io.OutputStreamWriter(
											new java.io.FileOutputStream(fileName_tFileOutputDelimited_1, true),
											"UTF-8"));

							resourceMap.put("out_tFileOutputDelimited_1", outtFileOutputDelimited_1);
							resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

							/**
							 * [tFileOutputDelimited_1 begin ] stop
							 */

							/**
							 * [tExtractJSONFields_2 begin ] start
							 */

							ok_Hash.put("tExtractJSONFields_2", false);
							start_Hash.put("tExtractJSONFields_2", System.currentTimeMillis());

							currentComponent = "tExtractJSONFields_2";

							if (execStat) {
								runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "film1");
							}

							int tos_count_tExtractJSONFields_2 = 0;

							int nb_line_tExtractJSONFields_2 = 0;
							String jsonStr_tExtractJSONFields_2 = "";

							class JsonPathCache_tExtractJSONFields_2 {
								final java.util.Map<String, com.jayway.jsonpath.JsonPath> jsonPathString2compiledJsonPath = new java.util.HashMap<String, com.jayway.jsonpath.JsonPath>();

								public com.jayway.jsonpath.JsonPath getCompiledJsonPath(String jsonPath) {
									if (jsonPathString2compiledJsonPath.containsKey(jsonPath)) {
										return jsonPathString2compiledJsonPath.get(jsonPath);
									} else {
										com.jayway.jsonpath.JsonPath compiledLoopPath = com.jayway.jsonpath.JsonPath
												.compile(jsonPath);
										jsonPathString2compiledJsonPath.put(jsonPath, compiledLoopPath);
										return compiledLoopPath;
									}
								}
							}

							JsonPathCache_tExtractJSONFields_2 jsonPathCache_tExtractJSONFields_2 = new JsonPathCache_tExtractJSONFields_2();

							/**
							 * [tExtractJSONFields_2 begin ] stop
							 */

							/**
							 * [tREST_2 begin ] start
							 */

							ok_Hash.put("tREST_2", false);
							start_Hash.put("tREST_2", System.currentTimeMillis());

							currentComponent = "tREST_2";

							int tos_count_tREST_2 = 0;

							String endpoint_tREST_2 = "https://api.themoviedb.org/3/movie/" + globalMap.get("films2.id")
									+ "?language=fr-FR";

							String trustStoreFile_tREST_2 = System.getProperty("javax.net.ssl.trustStore");
							String trustStoreType_tREST_2 = System.getProperty("javax.net.ssl.trustStoreType");
							String trustStorePWD_tREST_2 = System.getProperty("javax.net.ssl.trustStorePassword");

							String keyStoreFile_tREST_2 = System.getProperty("javax.net.ssl.keyStore");
							String keyStoreType_tREST_2 = System.getProperty("javax.net.ssl.keyStoreType");
							String keyStorePWD_tREST_2 = System.getProperty("javax.net.ssl.keyStorePassword");

							com.sun.jersey.api.client.config.ClientConfig config_tREST_2 = new com.sun.jersey.api.client.config.DefaultClientConfig();
							javax.net.ssl.SSLContext ctx_tREST_2 = javax.net.ssl.SSLContext.getInstance("SSL");

							javax.net.ssl.TrustManager[] tms_tREST_2 = null;
							if (trustStoreFile_tREST_2 != null && trustStoreType_tREST_2 != null) {
								char[] password_tREST_2 = null;
								if (trustStorePWD_tREST_2 != null)
									password_tREST_2 = trustStorePWD_tREST_2.toCharArray();
								java.security.KeyStore trustStore_tREST_2 = java.security.KeyStore
										.getInstance(trustStoreType_tREST_2);
								trustStore_tREST_2.load(new java.io.FileInputStream(trustStoreFile_tREST_2),
										password_tREST_2);

								javax.net.ssl.TrustManagerFactory tmf_tREST_2 = javax.net.ssl.TrustManagerFactory
										.getInstance(javax.net.ssl.KeyManagerFactory.getDefaultAlgorithm());
								tmf_tREST_2.init(trustStore_tREST_2);
								tms_tREST_2 = tmf_tREST_2.getTrustManagers();
							}

							javax.net.ssl.KeyManager[] kms_tREST_2 = null;
							if (keyStoreFile_tREST_2 != null && keyStoreType_tREST_2 != null) {
								char[] password_tREST_2 = null;
								if (keyStorePWD_tREST_2 != null)
									password_tREST_2 = keyStorePWD_tREST_2.toCharArray();
								java.security.KeyStore keyStore_tREST_2 = java.security.KeyStore
										.getInstance(keyStoreType_tREST_2);
								keyStore_tREST_2.load(new java.io.FileInputStream(keyStoreFile_tREST_2),
										password_tREST_2);

								javax.net.ssl.KeyManagerFactory kmf_tREST_2 = javax.net.ssl.KeyManagerFactory
										.getInstance(javax.net.ssl.KeyManagerFactory.getDefaultAlgorithm());
								kmf_tREST_2.init(keyStore_tREST_2, password_tREST_2);
								kms_tREST_2 = kmf_tREST_2.getKeyManagers();
							}

							ctx_tREST_2.init(kms_tREST_2, tms_tREST_2, null);
							config_tREST_2.getProperties().put(
									com.sun.jersey.client.urlconnection.HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
									new com.sun.jersey.client.urlconnection.HTTPSProperties(
											new javax.net.ssl.HostnameVerifier() {

												public boolean verify(String hostName,
														javax.net.ssl.SSLSession session) {
													return true;
												}
											}, ctx_tREST_2));

							com.sun.jersey.api.client.Client restClient_tREST_2 = com.sun.jersey.api.client.Client
									.create(config_tREST_2);

							java.util.Map<String, Object> headers_tREST_2 = new java.util.HashMap<String, Object>();

							headers_tREST_2.put("Authorization",
									"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ZDA2OGNiNzdmMDdiYWQ1NjQ4YmVjNzMyZDdjZmU4MiIsInN1YiI6IjY1YThkZTc3MGU1YWJhMDEzODdkZTIwOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.EDcWYM5XLM8Ku9ab4qhMOhwFnNaJqoIykiFCW6KT_6Q");

							Object transfer_encoding_tREST_2 = headers_tREST_2.get("Transfer-Encoding");
							if (transfer_encoding_tREST_2 != null && "chunked".equals(transfer_encoding_tREST_2)) {
								restClient_tREST_2.setChunkedEncodingSize(4096);
							}

							com.sun.jersey.api.client.WebResource restResource_tREST_2;
							if (endpoint_tREST_2 != null && !("").equals(endpoint_tREST_2)) {
								restResource_tREST_2 = restClient_tREST_2.resource(endpoint_tREST_2);
							} else {
								throw new IllegalArgumentException("url can't be empty!");
							}

							com.sun.jersey.api.client.ClientResponse errorResponse_tREST_2 = null;
							String restResponse_tREST_2 = "";
							try {

								com.sun.jersey.api.client.WebResource.Builder builder_tREST_2 = null;
								for (java.util.Map.Entry<String, Object> header_tREST_2 : headers_tREST_2.entrySet()) {
									if (builder_tREST_2 == null) {
										builder_tREST_2 = restResource_tREST_2.header(header_tREST_2.getKey(),
												header_tREST_2.getValue());
									} else {
										builder_tREST_2.header(header_tREST_2.getKey(), header_tREST_2.getValue());
									}
								}

								if (builder_tREST_2 != null) {
									restResponse_tREST_2 = builder_tREST_2.get(String.class);
								} else {
									restResponse_tREST_2 = restResource_tREST_2.get(String.class);
								}

							} catch (com.sun.jersey.api.client.UniformInterfaceException ue) {
								globalMap.put("tREST_2_ERROR_MESSAGE", ue.getMessage());
								errorResponse_tREST_2 = ue.getResponse();
							}

							// for output

							film1 = new film1Struct();
							if (errorResponse_tREST_2 != null) {
								film1.ERROR_CODE = errorResponse_tREST_2.getStatus();
								if (film1.ERROR_CODE != 204) {
									film1.Body = errorResponse_tREST_2.getEntity(String.class);
								}
							} else {
								film1.Body = restResponse_tREST_2;
							}

							/**
							 * [tREST_2 begin ] stop
							 */

							/**
							 * [tREST_2 main ] start
							 */

							currentComponent = "tREST_2";

							tos_count_tREST_2++;

							/**
							 * [tREST_2 main ] stop
							 */

							/**
							 * [tREST_2 process_data_begin ] start
							 */

							currentComponent = "tREST_2";

							/**
							 * [tREST_2 process_data_begin ] stop
							 */

							/**
							 * [tExtractJSONFields_2 main ] start
							 */

							currentComponent = "tExtractJSONFields_2";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "film1"

								);
							}

							if (film1.Body != null) {// C_01
								jsonStr_tExtractJSONFields_2 = film1.Body.toString();

								film2 = null;

								String loopPath_tExtractJSONFields_2 = "$";
								java.util.List<Object> resultset_tExtractJSONFields_2 = new java.util.ArrayList<Object>();

								boolean isStructError_tExtractJSONFields_2 = true;
								com.jayway.jsonpath.ReadContext document_tExtractJSONFields_2 = null;
								try {
									document_tExtractJSONFields_2 = com.jayway.jsonpath.JsonPath
											.parse(jsonStr_tExtractJSONFields_2);
									com.jayway.jsonpath.JsonPath compiledLoopPath_tExtractJSONFields_2 = jsonPathCache_tExtractJSONFields_2
											.getCompiledJsonPath(loopPath_tExtractJSONFields_2);
									Object result_tExtractJSONFields_2 = document_tExtractJSONFields_2.read(
											compiledLoopPath_tExtractJSONFields_2, net.minidev.json.JSONObject.class);
									if (result_tExtractJSONFields_2 instanceof net.minidev.json.JSONArray) {
										resultset_tExtractJSONFields_2 = (net.minidev.json.JSONArray) result_tExtractJSONFields_2;
									} else {
										resultset_tExtractJSONFields_2.add(result_tExtractJSONFields_2);
									}

									isStructError_tExtractJSONFields_2 = false;
								} catch (java.lang.Exception ex_tExtractJSONFields_2) {
									globalMap.put("tExtractJSONFields_2_ERROR_MESSAGE",
											ex_tExtractJSONFields_2.getMessage());
									System.err.println(ex_tExtractJSONFields_2.getMessage());
								}

								String jsonPath_tExtractJSONFields_2 = null;
								com.jayway.jsonpath.JsonPath compiledJsonPath_tExtractJSONFields_2 = null;

								Object value_tExtractJSONFields_2 = null;

								Object root_tExtractJSONFields_2 = null;
								for (int i_tExtractJSONFields_2 = 0; isStructError_tExtractJSONFields_2
										|| (i_tExtractJSONFields_2 < resultset_tExtractJSONFields_2
												.size()); i_tExtractJSONFields_2++) {
									if (!isStructError_tExtractJSONFields_2) {
										Object row_tExtractJSONFields_2 = resultset_tExtractJSONFields_2
												.get(i_tExtractJSONFields_2);
										film2 = null;
										film2 = new film2Struct();
										nb_line_tExtractJSONFields_2++;
										try {
											jsonPath_tExtractJSONFields_2 = "id";
											compiledJsonPath_tExtractJSONFields_2 = jsonPathCache_tExtractJSONFields_2
													.getCompiledJsonPath(jsonPath_tExtractJSONFields_2);

											try {

												value_tExtractJSONFields_2 = compiledJsonPath_tExtractJSONFields_2
														.read(row_tExtractJSONFields_2);

												film2.id = value_tExtractJSONFields_2 == null ?

														null

														: value_tExtractJSONFields_2.toString();
											} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_2) {
												globalMap.put("tExtractJSONFields_2_ERROR_MESSAGE",
														e_tExtractJSONFields_2.getMessage());
												film2.id =

														null

												;
											}
											jsonPath_tExtractJSONFields_2 = "budget";
											compiledJsonPath_tExtractJSONFields_2 = jsonPathCache_tExtractJSONFields_2
													.getCompiledJsonPath(jsonPath_tExtractJSONFields_2);

											try {

												value_tExtractJSONFields_2 = compiledJsonPath_tExtractJSONFields_2
														.read(row_tExtractJSONFields_2);

												film2.budget = value_tExtractJSONFields_2 == null ?

														null

														: value_tExtractJSONFields_2.toString();
											} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_2) {
												globalMap.put("tExtractJSONFields_2_ERROR_MESSAGE",
														e_tExtractJSONFields_2.getMessage());
												film2.budget =

														null

												;
											}
											jsonPath_tExtractJSONFields_2 = "genres[*].name";
											compiledJsonPath_tExtractJSONFields_2 = jsonPathCache_tExtractJSONFields_2
													.getCompiledJsonPath(jsonPath_tExtractJSONFields_2);

											try {

												value_tExtractJSONFields_2 = compiledJsonPath_tExtractJSONFields_2
														.read(row_tExtractJSONFields_2);

												film2.genres = value_tExtractJSONFields_2 == null ?

														null

														: value_tExtractJSONFields_2.toString();
											} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_2) {
												globalMap.put("tExtractJSONFields_2_ERROR_MESSAGE",
														e_tExtractJSONFields_2.getMessage());
												film2.genres =

														null

												;
											}
											jsonPath_tExtractJSONFields_2 = "original_language";
											compiledJsonPath_tExtractJSONFields_2 = jsonPathCache_tExtractJSONFields_2
													.getCompiledJsonPath(jsonPath_tExtractJSONFields_2);

											try {

												value_tExtractJSONFields_2 = compiledJsonPath_tExtractJSONFields_2
														.read(row_tExtractJSONFields_2);

												film2.original_language = value_tExtractJSONFields_2 == null ?

														null

														: value_tExtractJSONFields_2.toString();
											} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_2) {
												globalMap.put("tExtractJSONFields_2_ERROR_MESSAGE",
														e_tExtractJSONFields_2.getMessage());
												film2.original_language =

														null

												;
											}
											jsonPath_tExtractJSONFields_2 = "original_title";
											compiledJsonPath_tExtractJSONFields_2 = jsonPathCache_tExtractJSONFields_2
													.getCompiledJsonPath(jsonPath_tExtractJSONFields_2);

											try {

												value_tExtractJSONFields_2 = compiledJsonPath_tExtractJSONFields_2
														.read(row_tExtractJSONFields_2);

												film2.original_title = value_tExtractJSONFields_2 == null ?

														null

														: value_tExtractJSONFields_2.toString();
											} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_2) {
												globalMap.put("tExtractJSONFields_2_ERROR_MESSAGE",
														e_tExtractJSONFields_2.getMessage());
												film2.original_title =

														null

												;
											}
											jsonPath_tExtractJSONFields_2 = "title";
											compiledJsonPath_tExtractJSONFields_2 = jsonPathCache_tExtractJSONFields_2
													.getCompiledJsonPath(jsonPath_tExtractJSONFields_2);

											try {

												value_tExtractJSONFields_2 = compiledJsonPath_tExtractJSONFields_2
														.read(row_tExtractJSONFields_2);

												film2.title = value_tExtractJSONFields_2 == null ?

														null

														: value_tExtractJSONFields_2.toString();
											} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_2) {
												globalMap.put("tExtractJSONFields_2_ERROR_MESSAGE",
														e_tExtractJSONFields_2.getMessage());
												film2.title =

														null

												;
											}
											jsonPath_tExtractJSONFields_2 = "overview";
											compiledJsonPath_tExtractJSONFields_2 = jsonPathCache_tExtractJSONFields_2
													.getCompiledJsonPath(jsonPath_tExtractJSONFields_2);

											try {

												value_tExtractJSONFields_2 = compiledJsonPath_tExtractJSONFields_2
														.read(row_tExtractJSONFields_2);

												film2.overview = value_tExtractJSONFields_2 == null ?

														null

														: value_tExtractJSONFields_2.toString();
											} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_2) {
												globalMap.put("tExtractJSONFields_2_ERROR_MESSAGE",
														e_tExtractJSONFields_2.getMessage());
												film2.overview =

														null

												;
											}
											jsonPath_tExtractJSONFields_2 = "poster_path";
											compiledJsonPath_tExtractJSONFields_2 = jsonPathCache_tExtractJSONFields_2
													.getCompiledJsonPath(jsonPath_tExtractJSONFields_2);

											try {

												value_tExtractJSONFields_2 = compiledJsonPath_tExtractJSONFields_2
														.read(row_tExtractJSONFields_2);

												film2.poster_path = value_tExtractJSONFields_2 == null ?

														null

														: value_tExtractJSONFields_2.toString();
											} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_2) {
												globalMap.put("tExtractJSONFields_2_ERROR_MESSAGE",
														e_tExtractJSONFields_2.getMessage());
												film2.poster_path =

														null

												;
											}
											jsonPath_tExtractJSONFields_2 = "production_companies[*].name";
											compiledJsonPath_tExtractJSONFields_2 = jsonPathCache_tExtractJSONFields_2
													.getCompiledJsonPath(jsonPath_tExtractJSONFields_2);

											try {

												value_tExtractJSONFields_2 = compiledJsonPath_tExtractJSONFields_2
														.read(row_tExtractJSONFields_2);

												film2.production_companies = value_tExtractJSONFields_2 == null ?

														null

														: value_tExtractJSONFields_2.toString();
											} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_2) {
												globalMap.put("tExtractJSONFields_2_ERROR_MESSAGE",
														e_tExtractJSONFields_2.getMessage());
												film2.production_companies =

														null

												;
											}
											jsonPath_tExtractJSONFields_2 = "release_date";
											compiledJsonPath_tExtractJSONFields_2 = jsonPathCache_tExtractJSONFields_2
													.getCompiledJsonPath(jsonPath_tExtractJSONFields_2);

											try {

												value_tExtractJSONFields_2 = compiledJsonPath_tExtractJSONFields_2
														.read(row_tExtractJSONFields_2);

												film2.release_date = value_tExtractJSONFields_2 == null ?

														null

														: value_tExtractJSONFields_2.toString();
											} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_2) {
												globalMap.put("tExtractJSONFields_2_ERROR_MESSAGE",
														e_tExtractJSONFields_2.getMessage());
												film2.release_date =

														null

												;
											}
											jsonPath_tExtractJSONFields_2 = "revenue";
											compiledJsonPath_tExtractJSONFields_2 = jsonPathCache_tExtractJSONFields_2
													.getCompiledJsonPath(jsonPath_tExtractJSONFields_2);

											try {

												value_tExtractJSONFields_2 = compiledJsonPath_tExtractJSONFields_2
														.read(row_tExtractJSONFields_2);

												film2.revenue = value_tExtractJSONFields_2 == null ?

														null

														: value_tExtractJSONFields_2.toString();
											} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_2) {
												globalMap.put("tExtractJSONFields_2_ERROR_MESSAGE",
														e_tExtractJSONFields_2.getMessage());
												film2.revenue =

														null

												;
											}
											jsonPath_tExtractJSONFields_2 = "runtime";
											compiledJsonPath_tExtractJSONFields_2 = jsonPathCache_tExtractJSONFields_2
													.getCompiledJsonPath(jsonPath_tExtractJSONFields_2);

											try {

												value_tExtractJSONFields_2 = compiledJsonPath_tExtractJSONFields_2
														.read(row_tExtractJSONFields_2);

												film2.runtime = value_tExtractJSONFields_2 == null ?

														null

														: value_tExtractJSONFields_2.toString();
											} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_2) {
												globalMap.put("tExtractJSONFields_2_ERROR_MESSAGE",
														e_tExtractJSONFields_2.getMessage());
												film2.runtime =

														null

												;
											}
											jsonPath_tExtractJSONFields_2 = "vote_average";
											compiledJsonPath_tExtractJSONFields_2 = jsonPathCache_tExtractJSONFields_2
													.getCompiledJsonPath(jsonPath_tExtractJSONFields_2);

											try {

												value_tExtractJSONFields_2 = compiledJsonPath_tExtractJSONFields_2
														.read(row_tExtractJSONFields_2);

												film2.vote_average = value_tExtractJSONFields_2 == null ?

														null

														: value_tExtractJSONFields_2.toString();
											} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_2) {
												globalMap.put("tExtractJSONFields_2_ERROR_MESSAGE",
														e_tExtractJSONFields_2.getMessage());
												film2.vote_average =

														null

												;
											}
											jsonPath_tExtractJSONFields_2 = "vote_count";
											compiledJsonPath_tExtractJSONFields_2 = jsonPathCache_tExtractJSONFields_2
													.getCompiledJsonPath(jsonPath_tExtractJSONFields_2);

											try {

												value_tExtractJSONFields_2 = compiledJsonPath_tExtractJSONFields_2
														.read(row_tExtractJSONFields_2);

												film2.vote_count = value_tExtractJSONFields_2 == null ?

														null

														: value_tExtractJSONFields_2.toString();
											} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_2) {
												globalMap.put("tExtractJSONFields_2_ERROR_MESSAGE",
														e_tExtractJSONFields_2.getMessage());
												film2.vote_count =

														null

												;
											}
											jsonPath_tExtractJSONFields_2 = "tagline";
											compiledJsonPath_tExtractJSONFields_2 = jsonPathCache_tExtractJSONFields_2
													.getCompiledJsonPath(jsonPath_tExtractJSONFields_2);

											try {

												value_tExtractJSONFields_2 = compiledJsonPath_tExtractJSONFields_2
														.read(row_tExtractJSONFields_2);

												film2.tagline = value_tExtractJSONFields_2 == null ?

														null

														: value_tExtractJSONFields_2.toString();
											} catch (com.jayway.jsonpath.PathNotFoundException e_tExtractJSONFields_2) {
												globalMap.put("tExtractJSONFields_2_ERROR_MESSAGE",
														e_tExtractJSONFields_2.getMessage());
												film2.tagline =

														null

												;
											}
										} catch (java.lang.Exception ex_tExtractJSONFields_2) {
											globalMap.put("tExtractJSONFields_2_ERROR_MESSAGE",
													ex_tExtractJSONFields_2.getMessage());
											System.err.println(ex_tExtractJSONFields_2.getMessage());
											film2 = null;
										}

									}

									isStructError_tExtractJSONFields_2 = false;

//}

									tos_count_tExtractJSONFields_2++;

									/**
									 * [tExtractJSONFields_2 main ] stop
									 */

									/**
									 * [tExtractJSONFields_2 process_data_begin ] start
									 */

									currentComponent = "tExtractJSONFields_2";

									/**
									 * [tExtractJSONFields_2 process_data_begin ] stop
									 */
// Start of branch "film2"
									if (film2 != null) {

										/**
										 * [tFileOutputDelimited_1 main ] start
										 */

										currentComponent = "tFileOutputDelimited_1";

										if (execStat) {
											runStat.updateStatOnConnection(iterateId, 1, 1

													, "film2"

											);
										}

										StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();
										if (film2.id != null) {
											sb_tFileOutputDelimited_1.append(film2.id);
										}
										sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
										if (film2.budget != null) {
											sb_tFileOutputDelimited_1.append(film2.budget);
										}
										sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
										if (film2.genres != null) {
											sb_tFileOutputDelimited_1.append(film2.genres);
										}
										sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
										if (film2.original_language != null) {
											sb_tFileOutputDelimited_1.append(film2.original_language);
										}
										sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
										if (film2.original_title != null) {
											sb_tFileOutputDelimited_1.append(film2.original_title);
										}
										sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
										if (film2.title != null) {
											sb_tFileOutputDelimited_1.append(film2.title);
										}
										sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
										if (film2.overview != null) {
											sb_tFileOutputDelimited_1.append(film2.overview);
										}
										sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
										if (film2.poster_path != null) {
											sb_tFileOutputDelimited_1.append(film2.poster_path);
										}
										sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
										if (film2.production_companies != null) {
											sb_tFileOutputDelimited_1.append(film2.production_companies);
										}
										sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
										if (film2.release_date != null) {
											sb_tFileOutputDelimited_1.append(film2.release_date);
										}
										sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
										if (film2.revenue != null) {
											sb_tFileOutputDelimited_1.append(film2.revenue);
										}
										sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
										if (film2.runtime != null) {
											sb_tFileOutputDelimited_1.append(film2.runtime);
										}
										sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
										if (film2.vote_average != null) {
											sb_tFileOutputDelimited_1.append(film2.vote_average);
										}
										sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
										if (film2.vote_count != null) {
											sb_tFileOutputDelimited_1.append(film2.vote_count);
										}
										sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
										if (film2.tagline != null) {
											sb_tFileOutputDelimited_1.append(film2.tagline);
										}
										sb_tFileOutputDelimited_1.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);

										nb_line_tFileOutputDelimited_1++;
										resourceMap.put("nb_line_tFileOutputDelimited_1",
												nb_line_tFileOutputDelimited_1);

										outtFileOutputDelimited_1.write(sb_tFileOutputDelimited_1.toString());

										tos_count_tFileOutputDelimited_1++;

										/**
										 * [tFileOutputDelimited_1 main ] stop
										 */

										/**
										 * [tFileOutputDelimited_1 process_data_begin ] start
										 */

										currentComponent = "tFileOutputDelimited_1";

										/**
										 * [tFileOutputDelimited_1 process_data_begin ] stop
										 */

										/**
										 * [tFileOutputDelimited_1 process_data_end ] start
										 */

										currentComponent = "tFileOutputDelimited_1";

										/**
										 * [tFileOutputDelimited_1 process_data_end ] stop
										 */

									} // End of branch "film2"

									// end for
								}

							} // C_01

							/**
							 * [tExtractJSONFields_2 process_data_end ] start
							 */

							currentComponent = "tExtractJSONFields_2";

							/**
							 * [tExtractJSONFields_2 process_data_end ] stop
							 */

							/**
							 * [tREST_2 process_data_end ] start
							 */

							currentComponent = "tREST_2";

							/**
							 * [tREST_2 process_data_end ] stop
							 */

							/**
							 * [tREST_2 end ] start
							 */

							currentComponent = "tREST_2";

							ok_Hash.put("tREST_2", true);
							end_Hash.put("tREST_2", System.currentTimeMillis());

							/**
							 * [tREST_2 end ] stop
							 */

							/**
							 * [tExtractJSONFields_2 end ] start
							 */

							currentComponent = "tExtractJSONFields_2";

							globalMap.put("tExtractJSONFields_1_NB_LINE", nb_line_tExtractJSONFields_2);

							if (execStat) {
								runStat.updateStat(resourceMap, iterateId, 2, 0, "film1");
							}

							ok_Hash.put("tExtractJSONFields_2", true);
							end_Hash.put("tExtractJSONFields_2", System.currentTimeMillis());

							/**
							 * [tExtractJSONFields_2 end ] stop
							 */

							/**
							 * [tFileOutputDelimited_1 end ] start
							 */

							currentComponent = "tFileOutputDelimited_1";

							if (outtFileOutputDelimited_1 != null) {
								outtFileOutputDelimited_1.flush();
								outtFileOutputDelimited_1.close();
							}

							globalMap.put("tFileOutputDelimited_1_NB_LINE", nb_line_tFileOutputDelimited_1);
							globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);

							resourceMap.put("finish_tFileOutputDelimited_1", true);

							if (execStat) {
								runStat.updateStat(resourceMap, iterateId, 2, 0, "film2");
							}

							ok_Hash.put("tFileOutputDelimited_1", true);
							end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

							/**
							 * [tFileOutputDelimited_1 end ] stop
							 */

							if (execStat) {
								runStat.updateStatOnConnection("iterate1", 2, "exec" + NB_ITERATE_tREST_2);
							}

							/**
							 * [tFlowToIterate_1 process_data_end ] start
							 */

							currentComponent = "tFlowToIterate_1";

							/**
							 * [tFlowToIterate_1 process_data_end ] stop
							 */

						} // End of branch "films2"

						// end for
					}

				} // C_01

				/**
				 * [tExtractJSONFields_1 process_data_end ] start
				 */

				currentComponent = "tExtractJSONFields_1";

				/**
				 * [tExtractJSONFields_1 process_data_end ] stop
				 */

				/**
				 * [tREST_1 process_data_end ] start
				 */

				currentComponent = "tREST_1";

				/**
				 * [tREST_1 process_data_end ] stop
				 */

				/**
				 * [tREST_1 end ] start
				 */

				currentComponent = "tREST_1";

				ok_Hash.put("tREST_1", true);
				end_Hash.put("tREST_1", System.currentTimeMillis());

				/**
				 * [tREST_1 end ] stop
				 */

				/**
				 * [tExtractJSONFields_1 end ] start
				 */

				currentComponent = "tExtractJSONFields_1";

				globalMap.put("tExtractJSONFields_1_NB_LINE", nb_line_tExtractJSONFields_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "films1");
				}

				ok_Hash.put("tExtractJSONFields_1", true);
				end_Hash.put("tExtractJSONFields_1", System.currentTimeMillis());

				/**
				 * [tExtractJSONFields_1 end ] stop
				 */

				/**
				 * [tFlowToIterate_1 end ] start
				 */

				currentComponent = "tFlowToIterate_1";

				globalMap.put("tFlowToIterate_1_NB_LINE", nb_line_tFlowToIterate_1);
				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "films2");
				}

				ok_Hash.put("tFlowToIterate_1", true);
				end_Hash.put("tFlowToIterate_1", System.currentTimeMillis());

				/**
				 * [tFlowToIterate_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tREST_1 finally ] start
				 */

				currentComponent = "tREST_1";

				/**
				 * [tREST_1 finally ] stop
				 */

				/**
				 * [tExtractJSONFields_1 finally ] start
				 */

				currentComponent = "tExtractJSONFields_1";

				/**
				 * [tExtractJSONFields_1 finally ] stop
				 */

				/**
				 * [tFlowToIterate_1 finally ] start
				 */

				currentComponent = "tFlowToIterate_1";

				/**
				 * [tFlowToIterate_1 finally ] stop
				 */

				/**
				 * [tREST_2 finally ] start
				 */

				currentComponent = "tREST_2";

				/**
				 * [tREST_2 finally ] stop
				 */

				/**
				 * [tExtractJSONFields_2 finally ] start
				 */

				currentComponent = "tExtractJSONFields_2";

				/**
				 * [tExtractJSONFields_2 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (resourceMap.get("finish_tFileOutputDelimited_1") == null) {

					java.io.Writer outtFileOutputDelimited_1 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_1");
					if (outtFileOutputDelimited_1 != null) {
						outtFileOutputDelimited_1.flush();
						outtFileOutputDelimited_1.close();
					}

				}

				/**
				 * [tFileOutputDelimited_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tREST_1_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final call_api call_apiClass = new call_api();

		int exitCode = call_apiClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = call_api.class.getClassLoader()
					.getResourceAsStream("film/call_api_1_0/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = call_api.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileDelete_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileDelete_1) {
			globalMap.put("tFileDelete_1_SUBPROCESS_STATE", -1);

			e_tFileDelete_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : call_api");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 121788 characters generated by Talend Open Studio for Data Integration on the
 * 24 janvier 2024, 11:17:14 CET
 ************************************************************************************************/