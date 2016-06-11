
/**
 * Contructor
 */
var filePreview = function() {
  this.fileElm = null;
  this.previewElm = null;
  this.target1 = 'image_file1';
  this.previewTarget1 = 'preview1';
  this.target2 = 'image_file2';
  this.previewTarget2 = 'preview2';
  this.target3 = 'image_file3';
  this.previewTarget3 = 'preview3';
  this.target4 = 'image_file4';
  this.previewTarget4 = 'preview4';


  var self = this;

  document.addEventListener('DOMContentLoaded', function() {
	    self.fileElm1 = document.getElementById(self.target1);
	    self.previewElm1 = document.getElementById(self.previewTarget1);
	    self.fileElm2 = document.getElementById(self.target2);
	    self.previewElm2 = document.getElementById(self.previewTarget2);
	    self.fileElm3 = document.getElementById(self.target3);
	    self.previewElm3 = document.getElementById(self.previewTarget3);
	    self.fileElm4 = document.getElementById(self.target4);
	    self.previewElm4 = document.getElementById(self.previewTarget4);
	    self.bindEvent();
	  }, false);
};

/**
 * catch file change event
 */
filePreview.prototype.bindEvent = function() {
  console.log('bindEvent');
  var self = this;

  //画像1
  this.fileElm1.addEventListener('change', function() {
    var file = self.fileElm1.files[0];
    if (!file.type.match(/(png|jpeg|jpg|gif|PNG|JPEG|JPG|GIF)$/)) return false;

    var fr  = new FileReader();
    fr.onload = function() {
      self.previewElm1.src = fr.result;
    }
    fr.readAsDataURL(file);
  }, false);


  //画像2
  this.fileElm2.addEventListener('change', function() {
	    var file = self.fileElm2.files[0];
	    if (!file.type.match(/(png|jpeg|jpg|gif|PNG|JPEG|JPG|GIF)$/)) return false;

	    var fr  = new FileReader();
	    fr.onload = function() {
	      self.previewElm2.src = fr.result;
	    }
	    fr.readAsDataURL(file);
  }, false);


  //画像3
  this.fileElm3.addEventListener('change', function() {
	    var file = self.fileElm3.files[0];
	    if (!file.type.match(/(png|jpeg|jpg|gif|PNG|JPEG|JPG|GIF)$/)) return false;

	    var fr  = new FileReader();
	    fr.onload = function() {
	      self.previewElm3.src = fr.result;
	    }
	    fr.readAsDataURL(file);
  }, false);


  //画像4
  this.fileElm4.addEventListener('change', function() {
	    var file = self.fileElm4.files[0];
	    if (!file.type.match(/(png|jpeg|jpg|gif|PNG|JPEG|JPG|GIF)$/)) return false;

	    var fr  = new FileReader();
	    fr.onload = function() {
	      self.previewElm4.src = fr.result;
	    }
	    fr.readAsDataURL(file);
  }, false);

};

if (!window.Matty) {
  /**
   * @class
   * Matty is parent class
   */
  window.Matty = new Object();
}

/**
 * generate instance
 */
window.Matty.filePreview = new filePreview();
