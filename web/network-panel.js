(()=>{"use strict";
const $=id=>document.getElementById(id),list=$("destinationList");
if(!list)return;
let nextId=1;
const copy=async(value,label)=>{if(!value){alert("Primero completá el campo.");return;}try{await navigator.clipboard.writeText(value);alert(label+" copiada.");}catch(e){const box=document.createElement("textarea");box.value=value;box.style.position="fixed";box.style.opacity="0";document.body.appendChild(box);box.select();try{document.execCommand("copy");alert(label+" copiada.");}catch(_){alert("No se pudo copiar automáticamente. Seleccioná y copiá el contenido manualmente.");}box.remove();}};
function addDestination(){const n=nextId++,el=document.createElement("div");el.className="destination-item";el.dataset.destination="";
el.innerHTML='<div class="destination-title"><label class="network-label">NUEVO DESTINO</label><button type="button" class="destination-remove" data-remove aria-label="Eliminar destino">×</button></div><label class="network-label">PLATAFORMA / NOMBRE</label><input class="network-input" data-name type="text" placeholder="Ej.: Facebook Live"><label class="network-label">DIRECCIÓN DEL SERVIDOR RTMP</label><input class="network-input" data-url type="url" inputmode="url" placeholder="rtmps://servidor/…"><label class="network-label">CLAVE DE TRANSMISIÓN</label><div class="secret-row"><input class="network-input" data-key type="password" autocomplete="off" placeholder="Pegá aquí la clave de emisión"><button type="button" class="secret-toggle" data-reveal>VER</button></div><div class="destination-actions"><button type="button" class="network-copy" data-copy-url>COPIAR DIRECCIÓN</button><button type="button" class="network-copy primary-copy" data-copy-key>COPIAR CLAVE</button></div>';
el.querySelector("[data-name]").id="destName"+n;list.appendChild(el);el.querySelector("[data-name]").focus();}
$("addDestination")?.addEventListener("click",addDestination);
list.addEventListener("click",e=>{const item=e.target.closest("[data-destination]");if(!item)return;
if(e.target.closest("[data-remove]")){if(list.querySelectorAll("[data-destination]").length>1)item.remove();else{item.querySelectorAll("input").forEach(x=>x.value="");item.querySelector("[data-name]").value="YouTube";}return;}
if(e.target.closest("[data-reveal]")){const input=item.querySelector("[data-key]"),btn=e.target.closest("[data-reveal]");input.type=input.type==="password"?"text":"password";btn.textContent=input.type==="password"?"VER":"OCULTAR";return;}
if(e.target.closest("[data-copy-url]"))copy(item.querySelector("[data-url]").value.trim(),"Dirección RTMP");
if(e.target.closest("[data-copy-key]"))copy(item.querySelector("[data-key]").value,"Clave de transmisión");
});
window.addEventListener("beforeunload",()=>{list.querySelectorAll("[data-key]").forEach(x=>x.value="");});
})();