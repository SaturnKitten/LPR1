
package br.com.projeto_petshop.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import br.com.projeto_petshop.dto.FuncionarioDTO;
import br.com.projeto_petshop.ctr.FuncionarioCTR;
import br.com.projeto_petshop.dto.ServicoDTO;
import br.com.projeto_petshop.ctr.ServicoCTR;

public class ServicoVIEW extends javax.swing.JInternalFrame {

    /**
     * Creates new form ServicoVIEW
     */
    
    FuncionarioDTO funcionarioDTO = new FuncionarioDTO();//Cria um objeto funcionarioDTO
    FuncionarioCTR funcionarioCTR = new FuncionarioCTR();//Cria um objeto funcionarioCTR
    ServicoDTO servicoDTO = new ServicoDTO();//Cria um objeto servicoDTO
    ServicoCTR servicoCTR = new ServicoCTR();//Cria um objeto servicoCTR
    
    int gravar_alterar;//Variável usada para saber se está alterando ou incluindo
    
    ResultSet rs;//Variável usada para preenchimento da tabela e dos campos
    DefaultTableModel modelo_jtl_consultar_funcionario;//Variável para guardar o modelo da tabela
    DefaultTableModel modelo_jtl_consultar_servico;//Variável para guardar o modelo da tabela
    
    //Método para centralizar o internalFrame
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width)/2, (d.height - this.getSize().height)/2);
    }//Fecha método setPosicao
    
    //Método utilizado para gravar os dados do animal
    private void gravar(){
        try{
            servicoDTO.setTipo_servico(tipo_servico.getText());
            servicoDTO.setValor_servico(Double.parseDouble(valor_servico.getText()));
            funcionarioDTO.setId_funcionario(Integer.parseInt(String.valueOf(
                jtl_consultar_funcionario.getValueAt(
                jtl_consultar_funcionario.getSelectedRow(), 0))));
            
            JOptionPane.showMessageDialog(null,
                    servicoCTR.inserirServico(servicoDTO, funcionarioDTO));
        }
        catch(Exception e){
            System.out.println("Erro ao gravar" + e.getMessage());
        }
    }//Fecha método gravar
    
    //Método utilizado para alterar os dados do animal
    private void alterar(){
        try{
            servicoDTO.setTipo_servico(tipo_servico.getText());
            servicoDTO.setValor_servico(Double.parseDouble(valor_servico.getText()));
            funcionarioDTO.setId_funcionario(Integer.parseInt(String.valueOf(
                jtl_consultar_funcionario.getValueAt(
                jtl_consultar_funcionario.getSelectedRow(), 0))));
            
            JOptionPane.showMessageDialog(null,
                    servicoCTR.alterarServico(servicoDTO, funcionarioDTO));
        }
        catch(Exception e){
            System.out.println("Erro ao alterar" + e.getMessage());
        }
    }//Fecha método alterar
    
    //Método utilizado para excluir os dados do tutor
    private void excluir(){
        if(JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o serviço?", "Aviso", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null, servicoCTR.excluirServico(servicoDTO));
        };
    }//Fecha método excluir
    
    //Método utilizado para liberar/bloquear os campos da tela
    private void liberaCampos(boolean a){
        tipo_servico.setEnabled(a);
        valor_servico.setEnabled(a);
        pesquisa_nome_funcionario.setEnabled(a);
        btn_pesquisar_funcionario.setEnabled(a);
        jtl_consultar_funcionario.setEnabled(a);
    }//Fecha método liberaCampos
    
    //Método utilizado para liberar os botões da tela
    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e){
        btn_novo.setEnabled(a);
        btn_salvar.setEnabled(b);
        btn_cancelar.setEnabled(c);
        btn_excluir.setEnabled(d);
        btn_sair.setEnabled(e);
    }//Fecha método liberaBotoes
    
    //Método utilizado para limpar os campos da tela
    private void limpaCampos(){
        tipo_servico.setText("");
        valor_servico.setText("");
        pesquisa_nome_funcionario.setText("");
        modelo_jtl_consultar_funcionario.setNumRows(0);
    }//Fecha método limpaCampos
    
    //Método utilizado para preencher/construir a jtable
    private void preencheTabelaServico(String tipo_servico){
        try{
            //Limpa todas as linhas
            modelo_jtl_consultar_servico.setNumRows(0);
            //Enquanto tiver linhas - faça
            servicoDTO.setTipo_servico(tipo_servico);
            rs = servicoCTR.consultarServico(servicoDTO, 1); //i = é a pesquisa por nome na classe DAO
            while(rs.next()){
                modelo_jtl_consultar_servico.addRow(new Object[]{
                   rs.getString("id_servico"),
                   rs.getString("tipo_servico"),
                });
            }
        }
        catch(Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
        finally{
            funcionarioCTR.CloseDB();
        }
    }//Fecha método preencheTabela
    
    //Método utilizado para preencher os campos da tela com valores do servico
    private void preencheCamposServico(int id_servico){
        try{
            servicoDTO.setId_servico(id_servico);
            rs = servicoCTR.consultarServico(servicoDTO, 2);//2 = é a pesquisa no id na classe DAO
            if(rs.next()){
            limpaCampos();
                
            tipo_servico.setText(rs.getString("tipo_servico"));
            valor_servico.setText(rs.getString("valor_servico"));
                        
            //Colocando os dados do tutor
            modelo_jtl_consultar_funcionario.setNumRows(0);
            modelo_jtl_consultar_funcionario.addRow(new Object[]{
                rs.getInt("id_funcionario"),
                rs.getString("nome_funcionario"),
            });
            jtl_consultar_funcionario.setRowSelectionInterval(0, 0);
            
            gravar_alterar = 2;
            liberaCampos(true);
            }//Fecha if
        }//Fecha try
        catch(Exception erTab){
            System.out.println("Erro SQL" + erTab);
        }
        finally{
            servicoCTR.CloseDB();
        }
    }//Fecha método preencheCamposAnimal
    
    //Método utilizado para preencher/construir a Jtable
    private void preencheTabelaFuncionario(String nome_funcionario){
        try{
            //Limpa todas as linhas
            modelo_jtl_consultar_funcionario.setNumRows(0);
            //Enquanto tiver linhas - faça
            funcionarioDTO.setNome_funcionario(nome_funcionario);
            rs = funcionarioCTR.consultarFuncionario(funcionarioDTO, 1);//1 = é a pesquisa por nome na classe DAO
            while(rs.next()){
                modelo_jtl_consultar_funcionario.addRow(new Object[]{
                    rs.getString("id_funcionario"),
                    rs.getString("nome_funcionario"),
                });
            }//Fecha while
        }//Fecha try
        catch(Exception erTab){
            System.out.println("Erro SQL: " + erTab);
        }
        finally{
            servicoCTR.CloseDB();
        }
    }//Fecha método preencheTabelaTutor
    
    public ServicoVIEW() {
        initComponents();
        
        //Chama todos os métodos liberaCampos
        liberaCampos(false);
        //Chama o método liberaBotoes
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_funcionario = (DefaultTableModel) jtl_consultar_funcionario.getModel();
        modelo_jtl_consultar_servico = (DefaultTableModel) jtl_consultar_servico.getModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tipo_servico = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        valor_servico = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtl_consultar_funcionario = new javax.swing.JTable();
        pesquisa_nome_funcionario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btn_pesquisar_funcionario = new javax.swing.JButton();
        btn_novo = new javax.swing.JButton();
        btn_salvar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        btn_excluir = new javax.swing.JButton();
        btn_sair = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_servico = new javax.swing.JTable();
        pesquisa_nome_servico = new javax.swing.JTextField();
        btn_pesquisar_servico = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("CADASTRAR SERVIÇO");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Tipo:");

        jLabel5.setText("Valor:");

        jtl_consultar_funcionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtl_consultar_funcionario);
        if (jtl_consultar_funcionario.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_funcionario.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_funcionario.getColumnModel().getColumn(0).setPreferredWidth(5);
            jtl_consultar_funcionario.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel6.setText("Funcionário:");

        btn_pesquisar_funcionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/pesquisar.png"))); // NOI18N
        btn_pesquisar_funcionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisar_funcionarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(valor_servico, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58))
                            .addComponent(tipo_servico)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(pesquisa_nome_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_pesquisar_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(18, Short.MAX_VALUE)
                                .addComponent(tipo_servico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(valor_servico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pesquisa_nome_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_pesquisar_funcionario)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/novo.png"))); // NOI18N
        btn_novo.setText("Novo");
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/salvar.png"))); // NOI18N
        btn_salvar.setText("Salvar");
        btn_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salvarActionPerformed(evt);
            }
        });

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/cancelar.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        btn_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/excluir.png"))); // NOI18N
        btn_excluir.setText("Excluir");
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        btn_sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/sair.png"))); // NOI18N
        btn_sair.setText("Sair");
        btn_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sairActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("CONSULTA");

        jLabel3.setText("Nome:");

        jtl_consultar_servico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtl_consultar_servico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_servicoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_servico);
        if (jtl_consultar_servico.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_servico.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_servico.getColumnModel().getColumn(1).setResizable(false);
        }

        btn_pesquisar_servico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_petshop/view/imagens/pesquisar.png"))); // NOI18N
        btn_pesquisar_servico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisar_servicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pesquisa_nome_servico)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_pesquisar_servico))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jLabel2)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pesquisa_nome_servico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(btn_pesquisar_servico)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(btn_novo)
                        .addGap(15, 15, 15)
                        .addComponent(btn_salvar)
                        .addGap(15, 15, 15)
                        .addComponent(btn_cancelar)
                        .addGap(19, 19, 19)
                        .addComponent(btn_excluir)
                        .addGap(15, 15, 15)
                        .addComponent(btn_sair))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(jLabel1)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_novo)
                    .addComponent(btn_salvar)
                    .addComponent(btn_cancelar)
                    .addComponent(btn_excluir)
                    .addComponent(btn_sair))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        liberaCampos(true);
        liberaBotoes(false, true, true, false, true);
        gravar_alterar = 1;
    }//GEN-LAST:event_btn_novoActionPerformed

    private void btn_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salvarActionPerformed
        if(gravar_alterar == 1){
            gravar();
            gravar_alterar = 0;
        }
        else{
            if(gravar_alterar == 2){
                alterar();
                gravar_alterar = 0;
            }
            else{
                JOptionPane.showMessageDialog(null, "Erro no sistema!");
            }
        }

        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
    }//GEN-LAST:event_btn_salvarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_servico.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alterar = 0;
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_servico.setNumRows(0);
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void btn_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_sairActionPerformed

    private void btn_pesquisar_funcionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisar_funcionarioActionPerformed
        preencheTabelaFuncionario(pesquisa_nome_funcionario.getText());
    }//GEN-LAST:event_btn_pesquisar_funcionarioActionPerformed

    private void btn_pesquisar_servicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisar_servicoActionPerformed
        preencheTabelaServico(pesquisa_nome_servico.getText());
    }//GEN-LAST:event_btn_pesquisar_servicoActionPerformed

    private void jtl_consultar_servicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_servicoMouseClicked
        //Pega o id do produto selecionado e chama preencheCampos
        preencheCamposServico(Integer.parseInt(String.valueOf(
            jtl_consultar_servico.getValueAt(
            jtl_consultar_servico.getSelectedRow(), 0))));
        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_jtl_consultar_servicoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_excluir;
    private javax.swing.JButton btn_novo;
    private javax.swing.JButton btn_pesquisar_funcionario;
    private javax.swing.JButton btn_pesquisar_servico;
    private javax.swing.JButton btn_sair;
    private javax.swing.JButton btn_salvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtl_consultar_funcionario;
    private javax.swing.JTable jtl_consultar_servico;
    private javax.swing.JTextField pesquisa_nome_funcionario;
    private javax.swing.JTextField pesquisa_nome_servico;
    private javax.swing.JTextField tipo_servico;
    private javax.swing.JTextField valor_servico;
    // End of variables declaration//GEN-END:variables
}
