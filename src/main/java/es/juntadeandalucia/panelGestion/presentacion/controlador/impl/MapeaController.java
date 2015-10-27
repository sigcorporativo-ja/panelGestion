/**
 * Empresa desarrolladora: GUADALTEL S.A.
 *
 * Autor: Junta de Andalucía
 *
 * Derechos de explotación propiedad de la Junta de Andalucía.
 *
 * Este programa es software libre: usted tiene derecho a redistribuirlo y/o modificarlo bajo los términos de la
 *
 * Licencia EUPL European Public License publicada por el organismo IDABC de la Comisión Europea, en su versión 1.0.
 * o posteriores.
 *
 * Este programa se distribuye de buena fe, pero SIN NINGUNA GARANTÍA, incluso sin las presuntas garantías implícitas
 * de USABILIDAD o ADECUACIÓN A PROPÓSITO CONCRETO. Para mas información consulte la Licencia EUPL European Public
 * License.
 *
 * Usted recibe una copia de la Licencia EUPL European Public License junto con este programa, si por algún motivo no
 * le es posible visualizarla, puede consultarla en la siguiente URL: http://ec.europa.eu/idabc/servlets/Doc?id=31099
 *
 * You should have received a copy of the EUPL European Public License along with this program. If not, see
 * http://ec.europa.eu/idabc/servlets/Doc?id=31096
 *
 * Vous devez avoir reçu une copie de la EUPL European Public License avec ce programme. Si non, voir
 * http://ec.europa.eu/idabc/servlets/Doc?id=30194
 *
 * Sie sollten eine Kopie der EUPL European Public License zusammen mit diesem Programm. Wenn nicht, finden Sie da
 * http://ec.europa.eu/idabc/servlets/Doc?id=29919
 */
/**
 * 
 */
package es.juntadeandalucia.panelGestion.presentacion.controlador.impl;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import es.juntadeandalucia.panelGestion.negocio.utiles.Geoserver;
import es.juntadeandalucia.panelGestion.negocio.utiles.PanelSettings;

/**
 * Seam controller that manages the Mapea services
 *
 * @author GUADALTEL S.A
 */
@AutoCreate
@Scope(ScopeType.CONVERSATION)
@Name("mapeacontroller")
public class MapeaController implements Serializable {

   /**
    * Generated serial version UID
    */
   private static final long serialVersionUID = 829232671083423873L;
   
   public MapeaController() { }

   public String getUrlForWMSLayer(String name, String wmsUrl, boolean transparence) {
      String urlWMSLayer = PanelSettings.mapeaUrl;
      
      urlWMSLayer = urlWMSLayer.concat("&layers=").concat("WMS*")
         .concat(name).concat("*").concat(wmsUrl).concat("*")
         .concat(name).concat("*").concat(String.valueOf(transparence));
      
      return urlWMSLayer;
   }
   
   public String getUrlForWFSLayer(String name, String workspace, String geometryType, Geoserver geoserver) {
      String urlWFSLayer = PanelSettings.mapeaUrl;
      
      String geometryTypeLayer = geometryType;
      if (!StringUtils.isEmpty(geometryTypeLayer)) {
         geometryTypeLayer = geometryTypeLayer.replace("MULTI", "M");
      }
      urlWFSLayer = urlWFSLayer.concat("&layers=").concat("WFST*").concat(name)
         .concat("*").concat(geoserver.getWFSUrl(workspace)).concat("*")
         .concat(workspace).concat(":").concat(name).concat("*").concat(geometryTypeLayer);
      
      return urlWFSLayer;
   }

   /**
    * TODO
    *
    * @param serviceUrl
    * @return
    */
   public String getUrlForGeosearch(String serviceUrl) {
      String mapeaGeosearchUrl = PanelSettings.mapeaUrl;
      
      mapeaGeosearchUrl = mapeaGeosearchUrl.concat("&geosearch=").concat(serviceUrl);
      
      return mapeaGeosearchUrl;
   }
}
